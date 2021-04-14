package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.CostCategory;
import com.spehof.piggy.service.CostCategoryService;
import com.spehof.piggy.service.MoneyMovementCategoryHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Spehof
 * @created 13/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/cost")
public class CostCategoryController {

    private final CostCategoryService costCategoryService;
    private final MoneyMovementCategoryHolderService moneyMovementCategoryHolderService;

    @Autowired
    public CostCategoryController(CostCategoryService costCategoryService,
                                  MoneyMovementCategoryHolderService moneyMovementCategoryHolderService) {
        this.costCategoryService = costCategoryService;
        this.moneyMovementCategoryHolderService = moneyMovementCategoryHolderService;
    }

    @GetMapping()
    public Set<CostCategory> getAll(@PathVariable(name = "id") Account account){
        return moneyMovementCategoryHolderService.getCostCategories(account.getClient());
    }

    @PostMapping()
    public void createNewCategory(@PathVariable(name = "id") Account account,
                                  @RequestBody CostCategory costCategory){
        CostCategory clientCostCategory = costCategoryService.create(account.getClient(), costCategory.getName());
        moneyMovementCategoryHolderService.addNewCostCategory(account.getClient(), clientCostCategory);
    }

    @DeleteMapping()
    public void deleteCategory(
            @PathVariable(name = "id") Account account,
            @RequestBody CostCategory costCategory){

        moneyMovementCategoryHolderService.removeCostCategory(account.getClient(), costCategory);
    }
}
