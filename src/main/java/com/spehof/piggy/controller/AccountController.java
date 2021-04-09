package com.spehof.piggy.controller;

import com.spehof.piggy.DAO.AccountDao;
import com.spehof.piggy.domain.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountDao accountDao;

    @Autowired
    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping("{id}")
    public Account getAccount(@PathVariable(name = "id") Account account){
        return account;
    }

    @PutMapping("{id}")
    public Account updateAccount(@PathVariable(name = "id") Account accountFromDb,
                                 @RequestBody Account accountFromApi){
        BeanUtils.copyProperties(accountFromApi, accountFromDb, "id");
        return accountDao.save(accountFromDb);
    }
}
