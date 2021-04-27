package com.spehof.piggy.controller.v1.broker;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Broker;
import com.spehof.piggy.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/brokers")
public class BrokerController {

    private final BrokerService brokerService;

    @Autowired
    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @GetMapping
    public List<Broker> getAll(@PathVariable(name = "id") Account account){
        return brokerService.getAll(account);
    }

    @GetMapping("{brokerId}")
    public Broker getOne(@PathVariable(name = "id") Account account,
                         @PathVariable(name = "brokerId") Broker broker){
        return brokerService.getOne(account, broker);
    }

    @PostMapping
    public Broker create(@PathVariable(name = "id") Account account,
                         @RequestBody Broker brokerFromApi){
        return brokerService.create(account, brokerFromApi.getBrokerTitle());
    }

    @DeleteMapping
    public void delete(@PathVariable(name = "id") Account account,
                       @RequestBody Broker brokerFromApi){
        brokerService.delete(account, brokerFromApi);
    }

    @PutMapping
    public Broker update(@PathVariable(name = "id") Account account,
                         @RequestBody Broker brokerFromApi){
        return brokerService.update(account, brokerFromApi);
    }
}
