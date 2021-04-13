package com.spehof.piggy.controller;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.EarningCategory;
import com.spehof.piggy.service.EarningCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Spehof
 * @created 13/04/2021
 */
@RestController
@RequestMapping("account/{id}/earning")
public class EarningCategoryController {

    private final EarningCategoryService earningCategoryService;

    @Autowired
    public EarningCategoryController(EarningCategoryService earningCategoryService) {
        this.earningCategoryService = earningCategoryService;
    }

    @GetMapping()
    public Set<EarningCategory> getAll(@PathVariable(name = "id") Account account){
        return earningCategoryService.getAll(account);
    }

    @PostMapping()
    public void createNewCategory(@PathVariable(name = "id") Account account,
                                  @RequestBody EarningCategory earningCategory){
        earningCategoryService.create(account.getClient(),
                earningCategory.getName());
    }

    @DeleteMapping()
    public void deleteCategory(
            @PathVariable(name = "id") Account account,
            @RequestBody EarningCategory earningCategory){

//        TODO refactor sending params
        earningCategoryService.remove(
                account.getClient(),
                earningCategory.getName());
    }
}
