package com.spehof.piggy.service;

import com.spehof.piggy.DAO.MoneyMovementCategoryDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.CostCategory;
import com.spehof.piggy.domain.EarningCategory;
import com.spehof.piggy.domain.MoneyMovementCategoryHolder;
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

    private final MoneyMovementCategoryDao moneyMovementCategoryDao;

    @Autowired
    public MoneyMovementCategoryHolderService(MoneyMovementCategoryDao moneyMovementCategoryDao) {
        this.moneyMovementCategoryDao = moneyMovementCategoryDao;
    }

    public MoneyMovementCategoryHolder create(Client client){
        MoneyMovementCategoryHolder moneyMovementCategoryHolder = new MoneyMovementCategoryHolder(client);
        return moneyMovementCategoryDao.save(moneyMovementCategoryHolder);
    }

    public void addNewCostCategory(Client client, CostCategory costCategory){
//        TODO refactor same row
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.setCostCategory(costCategory);
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);

    }

    public void addNewEarningCategory(Client client, EarningCategory earningCategory){
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.setEarningCategory(earningCategory);
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
    }

    public void removeEarningCategory(Client client, EarningCategory earningCategory){
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.removeEarningCategory(earningCategory);
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
    }

    public void removeCostCategory(Client client, CostCategory costCategory){
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.removeCostCategory(costCategory);
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
    }

    public Set<CostCategory> getCostCategories(Client client){
        return client.getMoneyMovementCategoryHolder().getCostCategories();
    }

    public Set<EarningCategory> getEarningCategories(Client client){
        return client.getMoneyMovementCategoryHolder().getEarningCategories();
    }
}
