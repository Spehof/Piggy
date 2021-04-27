package com.spehof.piggy.service;

import com.spehof.piggy.DAO.ClientDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.CostCategory;
import com.spehof.piggy.domain.EarningCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Spehof
 * @created 09/04/2021
 *
 * This service for control Clients account. Create, update etc.
 * It work with clients account using ancillary services:
 *      * MoneyMovementCategoryService - for manage client MoneyMovementCategory (create etc).
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
    private final MoneyMovementCategoryHolderService moneyMovementCategoryHolderService;
    private final EarningCategoryService earningCategoryService;
    private final CostCategoryService costCategoryService;
    private final FriendService friendService;
    private final BudgetService budgetService;
    private final NotificationService notificationService;
    private final GoalService goalService;
    private final MoneyHolderService moneyHolderService;
    private final TransactionService transactionService;

    @Autowired
    public ClientService(ClientDao clientDao,
                         AccountService accountService,
                         MoneyMovementCategoryHolderService moneyMovementCategoryHolderService,
                         EarningCategoryService earningCategoryService,
                         CostCategoryService costCategoryService,
                         FriendService friendService,
                         BudgetService budgetService,
                         NotificationService notificationService,
                         GoalService goalService,
                         MoneyHolderService moneyHolderService,
                         TransactionService transactionService) {

        this.clientDao = clientDao;
        this.accountService = accountService;
        this.moneyMovementCategoryHolderService = moneyMovementCategoryHolderService;
        this.earningCategoryService = earningCategoryService;
        this.costCategoryService = costCategoryService;
        this.friendService = friendService;
        this.budgetService = budgetService;
        this.notificationService = notificationService;
        this.goalService = goalService;
        this.moneyHolderService = moneyHolderService;
        this.transactionService = transactionService;
    }

    /**
     * Create a new Client in system.
     * @param client - minimal client data from ClientController
     * @return A new minimalistic filled Client class and save it in database
     * */
    public Client create(Client client){
        /** Set registration date when creating a new client */
        client.setRegistrationDate(LocalDateTime.now());

        /** Create client account */
        accountService.create(client);

        /** Set MoneyMovementCategoryHolder for holding Earning and Cost clients categories*/
//        TODO refactor
        client.setMoneyMovementCategoryHolder(moneyMovementCategoryHolderService.create(client));

        /** Creating new earning category */
        EarningCategory clientEarningCategory = earningCategoryService.create(client, "Test Earning Category");

//        TODO test data !!!
        /** Set new earning category clients moneyMovementCategoryHolder */
        moneyMovementCategoryHolderService.addNewEarningCategory(client, clientEarningCategory);


        /** Creating new cost category */
        CostCategory clientCostCategory = costCategoryService.create(client, "Test cost category");

        /** Set new cost category clients moneyMovementCategoryHolder */
        moneyMovementCategoryHolderService.addNewCostCategory(client, clientCostCategory);


        moneyHolderService.create(client, "My test wallet");

        friendService.create(client, "Niko");

        budgetService.create(client, 10000L);

        notificationService.create(client, "Alarm, it's test notification!");

        goalService.create(client, 1000000L, "For my new car");
//        TODO end of test data

        transactionService.create(client.getAccount(),
                client.getMoneyHolder(1L),
                client.getMoneyHolder(1L), new BigDecimal("100"));

        /** Saving created client in DB */
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
        BeanUtils.copyProperties(clientFromApi, clientFromDb,
                "id",
                "registrationDate",
                "account",
                "moneyMovementCategoryHolder",
                "moneyHolderTypes",
                "friends",
                "budgets",
                "notifications",
                "goals");

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
