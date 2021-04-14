package com.spehof.piggy.controller;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.EarningCategory;
import com.spehof.piggy.service.EarningCategoryService;
import com.spehof.piggy.service.MoneyMovementCategoryHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Spehof
 * @created 13/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/earning")
public class EarningCategoryController {

    private final EarningCategoryService earningCategoryService;
    private final MoneyMovementCategoryHolderService moneyMovementCategoryHolderService;

    @Autowired
    public EarningCategoryController(EarningCategoryService earningCategoryService,
                                     MoneyMovementCategoryHolderService moneyMovementCategoryHolderService) {
        this.earningCategoryService = earningCategoryService;
        this.moneyMovementCategoryHolderService = moneyMovementCategoryHolderService;
    }

    @GetMapping()
    public Set<EarningCategory> getAll(@PathVariable(name = "id") Account account){
        return moneyMovementCategoryHolderService.getEarningCategories(account.getClient());
    }

    @PostMapping()
    public void createNewCategory(@PathVariable(name = "id") Account account,
                                  @RequestBody EarningCategory earningCategory){
        EarningCategory clientEarningCategory =  earningCategoryService.create(account.getClient(), earningCategory.getName());
        moneyMovementCategoryHolderService.addNewEarningCategory(account.getClient(), clientEarningCategory);
    }

    @DeleteMapping()
    public void deleteCategory(
            @PathVariable(name = "id") Account account,
            @RequestBody EarningCategory earningCategory){

        moneyMovementCategoryHolderService.removeEarningCategory(account.getClient(), earningCategory);
    }
}
