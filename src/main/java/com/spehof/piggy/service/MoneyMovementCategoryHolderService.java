package com.spehof.piggy.service;

import com.spehof.piggy.DAO.EarningCategoryDao;
import com.spehof.piggy.DAO.MoneyMovementCategoryDao;
import com.spehof.piggy.domain.Client;
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

    private final MoneyMovementCategoryDao moneyMovementCategoryDao;
    private final EarningCategoryDao earningCategoryDao;

    @Autowired
    public MoneyMovementCategoryHolderService(MoneyMovementCategoryDao moneyMovementCategoryDao,
                                              EarningCategoryDao earningCategoryDao) {
        this.moneyMovementCategoryDao = moneyMovementCategoryDao;
        this.earningCategoryDao = earningCategoryDao;
    }

    public MoneyMovementCategoryHolder create(Client client){
        MoneyMovementCategoryHolder moneyMovementCategoryHolder = new MoneyMovementCategoryHolder(client);
        return moneyMovementCategoryDao.save(moneyMovementCategoryHolder);
    }

    public CostCategory addNewCostCategory(Client client, CostCategory costCategory){
//        TODO refactor same row
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.setCostCategory(costCategory);
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
        return costCategory;

    }

    public EarningCategory addNewEarningCategory(Client client, EarningCategory newEarningCategory){
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.setEarningCategory(newEarningCategory);
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
        return newEarningCategory;
    }

    public void removeEarningCategory(Client client, EarningCategory earningCategoryFromApi){
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        EarningCategory earningCategoryFromDb = clientMoneyMovementCategoryHolder.getEarningCategory(earningCategoryFromApi.getId());
        clientMoneyMovementCategoryHolder.removeEarningCategory(earningCategoryFromDb);
        earningCategoryDao.delete(earningCategoryFromDb);
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
    }

    public void removeCostCategory(Client client, CostCategory costCategoryFromApi){
        CostCategory costCategoryFromDb = client.getMoneyMovementCategoryHolder().getCostCategory(costCategoryFromApi.getId());
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        clientMoneyMovementCategoryHolder.removeCostCategory(costCategoryFromDb);
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
    }

    public Set<CostCategory> getCostCategories(Client client){
        return client.getMoneyMovementCategoryHolder().getCostCategories();
    }

    public Set<EarningCategory> getEarningCategories(Client client){
        return client.getMoneyMovementCategoryHolder().getEarningCategories();
    }

    public CostCategory updateCostCategory(Client client, CostCategory costCategoryFromApi) {
        CostCategory costCategoryFromDb = client.getMoneyMovementCategoryHolder().getCostCategory(costCategoryFromApi.getId());
        BeanUtils.copyProperties(costCategoryFromApi, costCategoryFromDb, "id", "moneyMovementCategoryHolder");
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
        return costCategoryFromApi;
    }

    public EarningCategory updateEarningCategory(Client client, EarningCategory earningCategoryFromApi) {
        EarningCategory earningCategoryFromDb = client.getMoneyMovementCategoryHolder().getEarningCategory(earningCategoryFromApi.getId());
        BeanUtils.copyProperties(earningCategoryFromApi, earningCategoryFromDb, "id", "moneyMovementCategoryHolder");
        MoneyMovementCategoryHolder clientMoneyMovementCategoryHolder = client.getMoneyMovementCategoryHolder();
        moneyMovementCategoryDao.save(clientMoneyMovementCategoryHolder);
        return earningCategoryFromApi;
    }
}
