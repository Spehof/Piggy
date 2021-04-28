package com.spehof.piggy.controller.v1.moneymovement;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.moneymovement.Cost;
import com.spehof.piggy.service.moneymovement.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/costs")
public class CostController {

    private final CostService costService;

    @Autowired
    public CostController(CostService costService) {
        this.costService = costService;
    }

    @GetMapping
    public List<Cost> getAll(@PathVariable(name = "id") Account account){
        return costService.getAll(account);
    }

    @PostMapping
    public Cost createCost(@PathVariable(name = "id") Account account,
                           @RequestBody Cost costFromApi){
        return costService.create(account,
                costFromApi.getCostCategory(),
                costFromApi.getMoneyHolder(),
                costFromApi.getAmount());
    }

    @DeleteMapping
    public void deleteCost(@PathVariable(name = "id") Account account,
                           @RequestBody Cost costFromApi){
        costService.delete(account, costFromApi);
    }

    @PutMapping()
    public Cost updateCost(@PathVariable(name = "id") Account account,
                           @RequestBody Cost costFromApi){
        return costService.update(account, costFromApi);
    }
}
