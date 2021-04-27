package com.spehof.piggy.controller.v1;

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
@RequestMapping("/api/v1/account/{id}/category/earnings")
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
        return moneyMovementCategoryHolderService.getEarningCategories(account.getUser());
    }

    @PostMapping()
    public EarningCategory createNewCategory(@PathVariable(name = "id") Account account,
                                  @RequestBody EarningCategory earningCategoryFromApi){
        EarningCategory clientEarningCategory =  earningCategoryService.create(account.getUser(), earningCategoryFromApi.getTitle());
        return moneyMovementCategoryHolderService.addNewEarningCategory(account.getUser(), clientEarningCategory);
    }

    @DeleteMapping()
    public void deleteCategory(
            @PathVariable(name = "id") Account account,
            @RequestBody EarningCategory earningCategoryFromApi){

        moneyMovementCategoryHolderService.removeEarningCategory(account.getUser(), earningCategoryFromApi);
    }

    @PutMapping()
    public EarningCategory updateCategory(@PathVariable(name = "id") Account account,
                                          @RequestBody EarningCategory earningCategoryFromApi){
        return moneyMovementCategoryHolderService.updateEarningCategory(account.getUser(), earningCategoryFromApi);
    }
}
