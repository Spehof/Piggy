package com.spehof.piggy.service;

import com.spehof.piggy.DAO.CostDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Cost;
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

    public Cost create(Account account, Long amount){
        Cost cost = new Cost(account, amount);
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
        BeanUtils.copyProperties(costFromApi, costFromDb, "id", "account");
        return costDao.save(costFromDb);
    }
}
