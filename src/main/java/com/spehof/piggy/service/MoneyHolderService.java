package com.spehof.piggy.service;

import com.spehof.piggy.DAO.MoneyHolderDao;
import com.spehof.piggy.domain.User;
import com.spehof.piggy.domain.MoneyHolder;
import com.spehof.piggy.exception.MoneyHolderStillUseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@Service
public class MoneyHolderService {

    private final MoneyHolderDao moneyHolderDao;
    private final CostService costService;
    private final EarningService earningService;

    @Autowired
    public MoneyHolderService(MoneyHolderDao moneyHolderDao,
                              CostService costService,
                              EarningService earningService) {
        this.moneyHolderDao = moneyHolderDao;
        this.costService = costService;
        this.earningService = earningService;
    }

    public MoneyHolder create(User user, String title){
        MoneyHolder moneyHolder = new MoneyHolder(user, title);
        user.setMoneyHolder(moneyHolder);
        return moneyHolderDao.save(moneyHolder);
    }

    public List<MoneyHolder> getAll(User user){
        return user.getMoneyHolders();
    }

    public void delete(User user, MoneyHolder moneyHolderFromApi){
        MoneyHolder moneyHolderFromDb = user.getMoneyHolder(moneyHolderFromApi.getId());
        if (this.checkUsing(moneyHolderFromDb)) throw new MoneyHolderStillUseException("Money holder " + moneyHolderFromDb.getTitle() + " still use");
        user.removeMoneyHolder(moneyHolderFromDb);
        moneyHolderDao.delete(moneyHolderFromDb);
    }

    public MoneyHolder update(User user, MoneyHolder moneyHolderFromApi){
        MoneyHolder moneyHolderFromDb = user.getMoneyHolder(moneyHolderFromApi.getId());
        if (this.checkUsing(moneyHolderFromDb)) throw new MoneyHolderStillUseException("Money holder " + moneyHolderFromDb.getTitle() + " still use");
        if (moneyHolderFromApi.getTitle() == null){
            BeanUtils.copyProperties(moneyHolderFromApi, moneyHolderFromDb, "id", "user", "title");
        } else {
            BeanUtils.copyProperties(moneyHolderFromApi, moneyHolderFromDb, "id", "user");
        }
        return moneyHolderDao.save(moneyHolderFromDb);
    }

    private boolean checkUsing(MoneyHolder moneyHolder){
        if (costService.checkUsing(moneyHolder) || earningService.checkUsing(moneyHolder)) {
            return true;
        } else {
            return false;
        }
    }
}
