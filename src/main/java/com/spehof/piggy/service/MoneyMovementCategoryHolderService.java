package com.spehof.piggy.service;

import com.spehof.piggy.DAO.EarningCategoryDao;
import com.spehof.piggy.DAO.MoneyMovementCategoryHolderDao;
import com.spehof.piggy.domain.User;
import com.spehof.piggy.domain.CostCategory;
import com.spehof.piggy.domain.EarningCategory;
import com.spehof.piggy.domain.MoneyMovementCategoryHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Spehof
 * @created 10/04/2021
 *
 * This class represent service for managing clients MoneyMovementCategoryHolder which holding all client
 * Money Movement Category
 * This class will be realize business logic to adding and removing clients money movement category
 */
@Service
public class MoneyMovementCategoryHolderService {

    private final MoneyMovementCategoryHolderDao moneyMovementCategoryHolderDao;
    private final EarningCategoryDao earningCategoryDao;

    @Autowired
    public MoneyMovementCategoryHolderService(MoneyMovementCategoryHolderDao moneyMovementCategoryHolderDao,
                                              EarningCategoryDao earningCategoryDao) {
        this.moneyMovementCategoryHolderDao = moneyMovementCategoryHolderDao;
        this.earningCategoryDao = earningCategoryDao;
    }

    public MoneyMovementCategoryHolder create(User user){
        MoneyMovementCategoryHolder moneyMovementCategoryHolder = new MoneyMovementCategoryHolder(user);
        return moneyMovementCategoryHolderDao.save(moneyMovementCategoryHolder);
    }

    public CostCategory addNewCostCategory(User user, CostCategory costCategory){
//        TODO refactor same row
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = user.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.setCostCategory(costCategory);
        moneyMovementCategoryHolderDao.save(clientMoneyMovementCategoryHolder);
        return costCategory;

    }

    public EarningCategory addNewEarningCategory(User user, EarningCategory newEarningCategory){
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = user.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.setEarningCategory(newEarningCategory);
        moneyMovementCategoryHolderDao.save(clientMoneyMovementCategoryHolder);
        return newEarningCategory;
    }

    public void removeEarningCategory(User user, EarningCategory earningCategoryFromApi){
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = user.getMoneyMovementCategoryHolder();
        EarningCategory earningCategoryFromDb = clientMoneyMovementCategoryHolder.getEarningCategory(earningCategoryFromApi.getId());
        clientMoneyMovementCategoryHolder.removeEarningCategory(earningCategoryFromDb);
        earningCategoryDao.delete(earningCategoryFromDb);
        moneyMovementCategoryHolderDao.save(clientMoneyMovementCategoryHolder);
    }

    public void removeCostCategory(User user, CostCategory costCategoryFromApi){
        CostCategory costCategoryFromDb = user.getMoneyMovementCategoryHolder().getCostCategory(costCategoryFromApi.getId());
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = user.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.removeCostCategory(costCategoryFromDb);
        moneyMovementCategoryHolderDao.save(clientMoneyMovementCategoryHolder);
    }

    public Set<CostCategory> getCostCategories(User user){
        return user.getMoneyMovementCategoryHolder().getCostCategories();
    }

    public Set<EarningCategory> getEarningCategories(User user){
        return user.getMoneyMovementCategoryHolder().getEarningCategories();
    }

    public CostCategory updateCostCategory(User user, CostCategory costCategoryFromApi) {
        CostCategory costCategoryFromDb = user.getMoneyMovementCategoryHolder().getCostCategory(costCategoryFromApi.getId());
        BeanUtils.copyProperties(costCategoryFromApi, costCategoryFromDb, "id", "moneyMovementCategoryHolder");
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = user.getMoneyMovementCategoryHolder();
        moneyMovementCategoryHolderDao.save(clientMoneyMovementCategoryHolder);
        return costCategoryFromApi;
    }

    public EarningCategory updateEarningCategory(User user, EarningCategory earningCategoryFromApi) {
        EarningCategory earningCategoryFromDb = user.getMoneyMovementCategoryHolder().getEarningCategory(earningCategoryFromApi.getId());
        BeanUtils.copyProperties(earningCategoryFromApi, earningCategoryFromDb, "id", "moneyMovementCategoryHolder");
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = user.getMoneyMovementCategoryHolder();
        moneyMovementCategoryHolderDao.save(clientMoneyMovementCategoryHolder);
        return earningCategoryFromApi;
    }
}
