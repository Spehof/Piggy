package com.spehof.piggy.service;

import com.spehof.piggy.DAO.AccountDao;
import com.spehof.piggy.DAO.UserDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Service
public class ClientService {

    private final UserDao userDao;
    private final AccountDao accountDao;

    @Autowired
    public ClientService(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    public Client create(Client client){
        client.setRegistrationDate(LocalDateTime.now());

        Account account = new Account();

        account.setCurrency(2);
        account.setClient(client);
        accountDao.save(account);
        return userDao.save(client);
    }

    public Client update(Client clientFromDb, Client clientFromApi){
        BeanUtils.copyProperties(clientFromApi, clientFromDb, "id");
        return userDao.save(clientFromDb);
    }

    public void delete(Client client){
        userDao.delete(client);
    }

    public List<Client> getAll(){
        return userDao.findAll();
    }
}
