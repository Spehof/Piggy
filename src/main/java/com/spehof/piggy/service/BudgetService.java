package com.spehof.piggy.service;

import com.spehof.piggy.DAO.BudgetDao;
import com.spehof.piggy.domain.Budget;
import com.spehof.piggy.domain.User;
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

    public Budget create(User user, Long value){
        Budget budget = new Budget(user, value);
        user.setBudget(budget);
        return budgetDao.save(budget);
    }

    public List<Budget> getAll(User user) {
        return user.getBudgets();
    }

    public void delete(User user, Budget budgetFromApi) {
        user.removeBudget(budgetFromApi);
        budgetDao.delete(budgetFromApi);
    }

    public Budget update(User user, Budget budgetFromApi) {
        Budget budgetFromDb = user.getBudget(budgetFromApi.getId());
        BeanUtils.copyProperties(budgetFromApi, budgetFromDb, "id", "user");
        return budgetDao.save(budgetFromDb);
    }
}
