package com.spehof.piggy.controller.v1.trading;

import com.spehof.piggy.domain.*;
import com.spehof.piggy.domain.trading.BrokerAccount;
import com.spehof.piggy.domain.trading.Trade;
import com.spehof.piggy.service.trading.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/trades")
public class TradeController {

    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping
    public List<Trade> getAll(@PathVariable(name = "id") Account account){
         return account.getBrokers().stream()
                 .flatMap(broker -> broker.getBrokerAccounts().stream())
                 .map(BrokerAccount::getTrades)
                 .flatMap(Collection::stream)
                 .collect(Collectors.toList());
    }

    @GetMapping("/subAccounts/{subAccountId}")
    public List<Trade> getFromSubAccount(@PathVariable(name = "id") Account account,
                                         @PathVariable (name = "subAccountId") BrokerAccount brokerAccount){
        return tradeService.getAll(brokerAccount);
    }

    /**
     * @param tradeFromApi - trade with assets name and amount
     * */
    @PostMapping("/subAccounts/{subaccountId}")
    public Trade create(@PathVariable(name = "id") Account account,
                        @PathVariable (name = "subaccountId") BrokerAccount brokerAccount,
                        @RequestBody Trade tradeFromApi){
        return tradeService.create(brokerAccount, tradeFromApi.getTradesAsset(), tradeFromApi.getAmount());
    }

    @DeleteMapping("/subAccounts/{subaccountId}")
    public void delete(@PathVariable(name = "id") Account account,
                       @PathVariable (name = "subaccountId") BrokerAccount brokerAccount,
                       @RequestBody Trade tradeFromApi){
        tradeService.delete(brokerAccount, tradeFromApi);
    }

    @PutMapping("/subAccounts/{subaccountId}")
    public Trade update(@PathVariable(name = "id") Account account,
                        @PathVariable (name = "subaccountId") BrokerAccount brokerAccount,
                        @RequestBody Trade tradeFromApi){
        return tradeService.update(brokerAccount, tradeFromApi);
    }
}
