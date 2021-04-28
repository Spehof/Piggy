package com.spehof.piggy.controller.v1.moneymovement;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.moneymovement.MoneyHolder;
import com.spehof.piggy.service.moneymovement.MoneyHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/moneyholders")
public class MoneyHolderController {

    private final MoneyHolderService moneyHolderService;

    @Autowired
    public MoneyHolderController(MoneyHolderService moneyHolderService) {
        this.moneyHolderService = moneyHolderService;
    }

    @GetMapping
    public List<MoneyHolder> getAll(@PathVariable(name = "id") Account account){
        return moneyHolderService.getAll(account.getUser());
    }

    @PostMapping
    public MoneyHolder create(@PathVariable(name = "id")Account account,
                              @RequestBody MoneyHolder moneyHolderFromApi){
        return moneyHolderService.create(account.getUser(),
                moneyHolderFromApi.getTitle());
    }

    @DeleteMapping
    public void delete(@PathVariable(name = "id")Account account,
                       @RequestBody MoneyHolder moneyHolderFromApi){
        moneyHolderService.delete(account.getUser(), moneyHolderFromApi);
    }

    @PutMapping
    public MoneyHolder update(@PathVariable(name = "id")Account account,
                              @RequestBody MoneyHolder moneyHolderFromApi){
        return moneyHolderService.update(account.getUser(), moneyHolderFromApi);
    }
}
