package com.spehof.piggy.service;

import com.spehof.piggy.DAO.MoneyHolderDao;
import com.spehof.piggy.domain.User;
import com.spehof.piggy.domain.MoneyHolder;
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

    @Autowired
    public MoneyHolderService(MoneyHolderDao moneyHolderDao) {
        this.moneyHolderDao = moneyHolderDao;
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
        user.removeMoneyHolder(moneyHolderFromDb);
        moneyHolderDao.delete(moneyHolderFromDb);
    }

    public MoneyHolder update(User user, MoneyHolder moneyHolderFromApi){
        MoneyHolder moneyHolderFromDb = user.getMoneyHolder(moneyHolderFromApi.getId());
        if (moneyHolderFromApi.getTitle() == null){
            BeanUtils.copyProperties(moneyHolderFromApi, moneyHolderFromDb, "id", "user");
        } else {
            BeanUtils.copyProperties(moneyHolderFromApi, moneyHolderFromDb, "id", "user");
        }
        return moneyHolderDao.save(moneyHolderFromDb);
    }
}
