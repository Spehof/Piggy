package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Broker;
import com.spehof.piggy.domain.BrokerSubAccount;
import com.spehof.piggy.service.BrokerSubAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/brokers/{brokerId}/subaccounts")
public class BrokerSubAccountController {

    private final BrokerSubAccountService brokerSubAccountService;

    @Autowired
    public BrokerSubAccountController(BrokerSubAccountService brokerSubAccountService) {
        this.brokerSubAccountService = brokerSubAccountService;
    }

    @GetMapping
    public List<BrokerSubAccount> getAll(@PathVariable(name = "id") Account account,
                                         @PathVariable(name = "brokerId") Broker broker){
        return brokerSubAccountService.getAll(account.getBroker(broker.getId()));
    }

    @GetMapping("{brokerSubAccountId}")
    public BrokerSubAccount getOne(@PathVariable(name = "id") Account account,
                                   @PathVariable(name = "brokerId") Broker broker,
                                   @PathVariable(name = "brokerSubAccountId") BrokerSubAccount brokerSubAccount){
        return brokerSubAccountService.getOne(account.getBroker(broker.getId()), brokerSubAccount);
    }

    @PostMapping
    public BrokerSubAccount create(@PathVariable(name = "id") Account account,
                                   @PathVariable(name = "brokerId") Broker broker,
                                   @RequestBody BrokerSubAccount brokerSubAccountFromApi){
        return brokerSubAccountService.create(account.getBroker(broker.getId()), brokerSubAccountFromApi.getTitle());
    }

    @DeleteMapping
    public void delete(@PathVariable(name = "id") Account account,
                       @PathVariable(name = "brokerId") Broker broker,
                       @RequestBody BrokerSubAccount brokerSubAccountFromApi){
        brokerSubAccountService.delete(account.getBroker(broker.getId()), brokerSubAccountFromApi);
    }

    @PutMapping
    public BrokerSubAccount update(@PathVariable(name = "id") Account account,
                                   @PathVariable(name = "brokerId") Broker broker,
                                   @RequestBody BrokerSubAccount brokerSubAccountFromApi){
        return brokerSubAccountService.update(account.getBroker(broker.getId()), brokerSubAccountFromApi);
    }
}
