package com.spehof.piggy.controller;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.CostCategory;
import com.spehof.piggy.domain.EarningCategory;
import com.spehof.piggy.service.CostCategoryService;
import com.spehof.piggy.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Spehof
 * @created 13/04/2021
 */
@RestController
@RequestMapping("account/{id}/cost")
public class CostCategoryController {

    private final CostCategoryService costCategoryService;

    @Autowired
    public CostCategoryController(CostCategoryService costCategoryService) {
        this.costCategoryService = costCategoryService;
    }

    @GetMapping()
    public Set<CostCategory> getAll(@PathVariable(name = "id") Account account){
        return costCategoryService.getAll(account);
    }

    @PostMapping()
    public void createNewCategory(@PathVariable(name = "id") Account account,
                                  @RequestBody CostCategory costCategory){
        costCategoryService.create(account.getClient(), costCategory.getName());
    }

    @DeleteMapping()
    public void deleteCategory(
            @PathVariable(name = "id") Account account,
            @RequestBody CostCategory costCategory){

//        TODO refactor sending params
        costCategoryService.remove(
                account.getClient(),
                costCategory.getName());
    }
}
