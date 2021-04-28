package com.spehof.piggy.service;

import com.spehof.piggy.DAO.CostDao;
import com.spehof.piggy.domain.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class CostService {

    private final CostDao costDao;

    @Autowired
    public CostService(CostDao costDao) {
        this.costDao = costDao;
    }

    public Cost create(Account account,
                       CostCategory costCategoryFromApi,
                       MoneyHolder moneyHolderFromApi,
                       Long amount){
        CostCategory costCategoryFromDb = account.getUser().getMoneyMovementCategoryHolder().getCostCategory(costCategoryFromApi.getTitle());
        MoneyHolder moneyHolderFromDb = account.getUser().getMoneyHolder(moneyHolderFromApi.getTitle());
        Cost cost = new Cost(account, costCategoryFromDb, moneyHolderFromDb, amount);
        account.setCost(cost);
        return costDao.save(cost);
    }

    public List<Cost> getAll(Account account) {
        return account.getCosts();
    }

    public void delete(Account account, Cost costFromApi) {
        account.removeCost(costFromApi);
        costDao.delete(costFromApi);
    }

    public Cost update(Account account, Cost costFromApi) {
        Cost costFromDb = account.getCost(costFromApi.getId());
        //        TODO write logic if moneyHolder of earningCategory not set for changing
        BeanUtils.copyProperties(costFromApi, costFromDb,
                "id",
                "account",
                "moneyHolder",
                "costCategory");
        return costDao.save(costFromDb);
    }

    public boolean checkUsing(MoneyHolder moneyHolderFromDb) {
        Boolean isUsing = moneyHolderFromDb.getUser().getAccount().getCosts().stream()
                .anyMatch(cost -> cost.getMoneyHolder().equals(moneyHolderFromDb));
        return isUsing;
    }
}
