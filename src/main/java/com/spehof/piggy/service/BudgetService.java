package com.spehof.piggy.service;

import com.spehof.piggy.DAO.BudgetDao;
import com.spehof.piggy.domain.Budget;
import com.spehof.piggy.domain.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class BudgetService {

    private final BudgetDao budgetDao;

    @Autowired
    public BudgetService(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }

    public Budget create(Client client, Long value){
        Budget budget = new Budget(client, value);
        client.setBudget(budget);
        return budgetDao.save(budget);
    }

    public List<Budget> getAll(Client client) {
        return client.getBudgets();
    }

    public void delete(Client client, Budget budgetFromApi) {
        client.removeBudget(budgetFromApi);
        budgetDao.delete(budgetFromApi);
    }

    public Budget update(Client client, Budget budgetFromApi) {
        Budget budgetFromDb = client.getBudget(budgetFromApi.getId());
        BeanUtils.copyProperties(budgetFromApi, budgetFromDb, "id", "client");
        return budgetDao.save(budgetFromDb);
    }
}
