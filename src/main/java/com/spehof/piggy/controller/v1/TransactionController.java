package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Transaction;
import com.spehof.piggy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAll(@PathVariable(name = "id") Account account){
        return transactionService.getAll(account);
    }

    @PostMapping
    public Transaction create(@PathVariable(name = "id") Account account,
                              @RequestBody Transaction transactionFromApi){
        return transactionService.create(account,
                transactionFromApi.getFromMoneyHolderId(),
                transactionFromApi.getToMoneyHolderId(),
                transactionFromApi.getAmount());
    }

    @DeleteMapping
    public void delete(@PathVariable(name = "id") Account account,
                       @RequestBody Transaction transactionFromApi){
        transactionService.delete(account, transactionFromApi);
    }

    @PutMapping
    public Transaction update(@PathVariable(name = "id") Account account,
                             @RequestBody Transaction transactionFromApi){
        return transactionService.update(account, transactionFromApi);
    }
}
