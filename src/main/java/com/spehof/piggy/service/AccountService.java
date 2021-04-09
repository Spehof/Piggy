package com.spehof.piggy.service;

import com.spehof.piggy.DAO.AccountDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Service
public class AccountService {

    private final AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void create(Client client){
        Account account = new Account();

        //for test
        account.setCurrency(2);
        account.setClient(client);
        accountDao.save(account);
    }

    public Account update(Account accountFromDb, Account accountFromApi){
        BeanUtils.copyProperties(accountFromApi, accountFromDb, "id");
        return accountDao.save(accountFromDb);
    }

    public void delete(Account account){
        accountDao.delete(account);
    }
}
