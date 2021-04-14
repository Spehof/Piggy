package com.spehof.piggy.controller;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("all")
    public List<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping("{id}")
    public Account get(@PathVariable(name = "id") Account account) {
        return account;
    }

    @PutMapping("{id}")
    public Account update(@PathVariable(name = "id") Account accountFromDb,
                          @RequestBody Account accountFromApi) {
        return accountService.update(accountFromDb, accountFromApi);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Account account) {
        accountService.delete(account);

    }
}
