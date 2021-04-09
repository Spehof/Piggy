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
 *
 * This service for control Clients account. Create, update etc.
 * It work with clients account using ancillary services:
 *      * MoneyMovementCategoryService - for manage client MoneyMovementCategory (create etc).
 *
 *      * MoneyHolderTypeService - for manage client MoneyHolderType.
 *      * FriendService - for manage client Friend (create in system, delete, etc).
 *      * BudgetService - for manage client Budgets (create, change, calculate).
 *      * NotificationService - for create and setting some Notification for client.
 *      * GoalService - for create, set or change client Goals in finance.
 */
@Service
public class ClientService {

    private final ClientDao clientDao;
    private final AccountService accountService;
    private final MoneyMovementCategoryService moneyMovementCategoryService;

    @Autowired
    public ClientService(ClientDao clientDao,
                         AccountService accountService,
                         MoneyMovementCategoryService moneyMovementCategoryService) {

        this.clientDao = clientDao;
        this.accountService = accountService;
        this.moneyMovementCategoryService = moneyMovementCategoryService;
    }

    /**
     * Create a new Client in system.
     * @param client - minimal client data from ClientController
     * @return A new minimalistic filled Client class and save it in database
     * */
    public Client create(Client client){
        client.setRegistrationDate(LocalDateTime.now());

        accountService.create(client);
//        TODO test data !!!
        for (String s : new String[]{"test category1", "test category2"}) {
            client.setMoneyMovementCategory(moneyMovementCategoryService.create(client, s));
        }
        return clientDao.save(client);
    }
    /**
     * Update existing client in system.
     * @param clientFromDb - client data from ClientController which will be changed
     * @param clientFromApi - current client from database on which to write new data and save again,
     *                     after validation and writing
     * @return - save updated client class in database and return it
     * */
    public Client update(Client clientFromDb, Client clientFromApi){
        BeanUtils.copyProperties(clientFromApi, clientFromDb, "id");
        return clientDao.save(clientFromDb);
    }

    /**
     * Delete existing client account.
     * @param client - client which need to delete
     * */
    public void delete(Client client){
        clientDao.delete(client);
    }

    /**
     * Get and return all client from database.
     * */
    public List<Client> getAll(){
        return clientDao.findAll();
    }
}
