package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.trading.Broker;
import com.spehof.piggy.domain.trading.BrokerAccount;
import com.spehof.piggy.service.trading.BrokerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/brokers/{brokerId}/accounts")
public class BrokerAccountController {

    private final BrokerAccountService brokerAccountService;

    @Autowired
    public BrokerAccountController(BrokerAccountService brokerAccountService) {
        this.brokerAccountService = brokerAccountService;
    }

    @GetMapping
    public List<BrokerAccount> getAll(@PathVariable(name = "id") Account account,
                                      @PathVariable(name = "brokerId") Broker broker){
        return brokerAccountService.getAll(account.getBroker(broker.getId()));
    }

    @GetMapping("{brokerSubAccountId}")
    public BrokerAccount getOne(@PathVariable(name = "id") Account account,
                                @PathVariable(name = "brokerId") Broker broker,
                                @PathVariable(name = "brokerSubAccountId") BrokerAccount brokerAccount){
        return brokerAccountService.getOne(account.getBroker(broker.getId()), brokerAccount);
    }

    @PostMapping
    public BrokerAccount create(@PathVariable(name = "id") Account account,
                                @PathVariable(name = "brokerId") Broker broker,
                                @RequestBody BrokerAccount brokerAccountFromApi){
        return brokerAccountService.create(account.getBroker(broker.getId()), brokerAccountFromApi.getTitle());
    }

    @DeleteMapping
    public void delete(@PathVariable(name = "id") Account account,
                       @PathVariable(name = "brokerId") Broker broker,
                       @RequestBody BrokerAccount brokerAccountFromApi){
        brokerAccountService.delete(account.getBroker(broker.getId()), brokerAccountFromApi);
    }

    @PutMapping
    public BrokerAccount update(@PathVariable(name = "id") Account account,
                                @PathVariable(name = "brokerId") Broker broker,
                                @RequestBody BrokerAccount brokerAccountFromApi){
        return brokerAccountService.update(account.getBroker(broker.getId()), brokerAccountFromApi);
    }
}
