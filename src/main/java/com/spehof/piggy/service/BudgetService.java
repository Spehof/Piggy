package com.spehof.piggy.service;

import com.spehof.piggy.DAO.BudgetDao;
import com.spehof.piggy.domain.Budget;
import com.spehof.piggy.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return budgetDao.save(budget);
    }
}
