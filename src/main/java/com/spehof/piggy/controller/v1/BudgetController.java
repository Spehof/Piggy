package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Budget;
import com.spehof.piggy.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public List<Budget> findAll(@PathVariable(name = "id") Account account){
        return budgetService.getAll(account.getClient());
    }

    @PostMapping
    public Budget createBudget(@PathVariable(name = "id") Account account,
                               @RequestBody Budget budget){
        return budgetService.create(account.getClient(), budget.getValue());
    }

    @DeleteMapping
    public void deleteBudget(@PathVariable(name = "id") Account account,
                             @RequestBody Budget budget){
        budgetService.delete(account.getClient(), budget);
    }

    @PutMapping("{oldBudgetId}")
    public Budget updateBudget(@PathVariable(name = "id") Account account,
                               @PathVariable(name = "oldBudgetId") Long oldBudgetId,
                               @RequestBody Budget budget){
        return budgetService.update(account.getClient(), budget, oldBudgetId);
    }
}
