package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Cost;
import com.spehof.piggy.service.CostService;
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
                           @RequestBody Cost cost){
        return costService.create(account, cost.getAmount());
    }

    @DeleteMapping
    public void deleteCost(@PathVariable(name = "id") Account account,
                           @RequestBody Cost cost){
        costService.delete(account, cost);
    }

    @PutMapping()
    public Cost updateCost(@PathVariable(name = "id") Account account,
                           @RequestBody Cost newCost){
        return costService.update(account, newCost);
    }
}
