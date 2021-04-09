package com.spehof.piggy.service;

import com.spehof.piggy.DAO.ClientDao;
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

    private final ClientDao clientDao;
    private final AccountService accountService;

    @Autowired
    public ClientService(ClientDao clientDao, AccountService accountService) {
        this.clientDao = clientDao;
        this.accountService = accountService;
    }

    public Client create(Client client){
        client.setRegistrationDate(LocalDateTime.now());

        accountService.create(client);
        return clientDao.save(client);
    }

    public Client update(Client clientFromDb, Client clientFromApi){
        BeanUtils.copyProperties(clientFromApi, clientFromDb, "id");
        return clientDao.save(clientFromDb);
    }

    public void delete(Client client){
        clientDao.delete(client);
    }

    public List<Client> getAll(){
        return clientDao.findAll();
    }
}
