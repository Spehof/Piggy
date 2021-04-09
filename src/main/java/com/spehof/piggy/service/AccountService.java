package com.spehof.piggy.service;

import com.spehof.piggy.DAO.AccountDao;
import com.spehof.piggy.DAO.ClientDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Service
public class AccountService {

    private final AccountDao accountDao;
    private final ClientDao clientDao;

    @Autowired
    public AccountService(AccountDao accountDao, ClientDao clientDao) {
        this.accountDao = accountDao;
        this.clientDao = clientDao;
    }

    public void create(Client client){
        Account account = new Account(2);

        account.setClient(client);
        accountDao.save(account);
    }

    public Account update(Account accountFromDb, Account accountFromApi){

        this.setAllFields(accountFromApi, accountFromDb);
        return accountDao.save(accountFromDb);
    }

    public void delete(Account account){
//        accountDao.delete(account);
        clientDao.delete(account.getClient());
    }

    public List<Account> getAll(){
        return accountDao.findAll();
    }

    private void setAllFields(Account source, Account target){
        target.setClient(source.getClient());
        target.setEarnings(source.getEarnings());
        target.setCosts(source.getCosts());
        target.setCurrency(source.getCurrency());

    }
}
