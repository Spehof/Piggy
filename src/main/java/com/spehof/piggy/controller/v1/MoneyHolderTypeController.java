package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.MoneyHolderType;
import com.spehof.piggy.service.MoneyHolderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 15/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/moneyholdertypes")
public class MoneyHolderTypeController {

    private  final MoneyHolderTypeService moneyHolderTypeService;

    @Autowired
    public MoneyHolderTypeController(MoneyHolderTypeService moneyHolderTypeService) {
        this.moneyHolderTypeService = moneyHolderTypeService;
    }

    @GetMapping
    public List<MoneyHolderType> getAll(@PathVariable(name = "id") Account account){
        return moneyHolderTypeService.getAll(account.getClient());
    }

    @GetMapping("{moneyholdertypeId}")
    public MoneyHolderType getOne(@PathVariable(name = "id") Account account,
                                  @PathVariable(name = "moneyholdertypeId") Long moneyHolderTypeId){
        return moneyHolderTypeService.getOne(account.getClient(), moneyHolderTypeId);
    }

    @PostMapping
    public MoneyHolderType createNewMoneyHolderType(@PathVariable(name = "id") Account account,
                                                    @RequestBody MoneyHolderType moneyHolderType){
        return moneyHolderTypeService.create(account.getClient(), moneyHolderType.getName());
    }

    @DeleteMapping
    public void deleteMoneyHolderType(@PathVariable(name = "id") Account account,
                                      @RequestBody MoneyHolderType moneyHolderType){
        moneyHolderTypeService.delete(account.getClient(), moneyHolderType);
    }

    @PutMapping("{MoneyHolderTypeId}")
    public MoneyHolderType updateMoneyHolderType(@PathVariable(name = "id") Account account,
                                                 @PathVariable(name = "MoneyHolderTypeId") Long oldMoneyHolderTypeId,
                                                 @RequestBody MoneyHolderType newMoneyHolderType){
        return moneyHolderTypeService.update(account.getClient(), newMoneyHolderType, oldMoneyHolderTypeId);
    }


}
