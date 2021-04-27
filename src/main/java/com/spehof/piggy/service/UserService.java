package com.spehof.piggy.service;

import com.spehof.piggy.DAO.UserDao;
import com.spehof.piggy.domain.User;
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
public class UserService {

    private final UserDao userDao;
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
    private final PortfolioService portfolioService;

    @Autowired
    public UserService(UserDao userDao,
                       AccountService accountService,
                       MoneyMovementCategoryHolderService moneyMovementCategoryHolderService,
                       EarningCategoryService earningCategoryService,
                       CostCategoryService costCategoryService,
                       FriendService friendService,
                       BudgetService budgetService,
                       NotificationService notificationService,
                       GoalService goalService,
                       MoneyHolderService moneyHolderService,
                       TransactionService transactionService,
                       PortfolioService portfolioService) {

        this.userDao = userDao;
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
        this.portfolioService = portfolioService;
    }

    /**
     * Create a new Client in system.
     * @param user - minimal client data from ClientController
     * @return A new minimalistic filled Client class and save it in database
     * */
    public User create(User user){
        /** Set registration date when creating a new client */
        user.setRegistrationDate(LocalDateTime.now());
        user.setMoneyMovementCategoryHolder(moneyMovementCategoryHolderService.create(user));
        userDao.save(user);

        /** Creating new earning category */
        EarningCategory clientEarningCategory = earningCategoryService.create(user, "Test Earning Category");
        /** Set new earning category clients moneyMovementCategoryHolder */
        moneyMovementCategoryHolderService.addNewEarningCategory(user, clientEarningCategory);

        /** Creating new cost category */
        CostCategory clientCostCategory = costCategoryService.create(user, "Test cost category");
        /** Set new cost category clients moneyMovementCategoryHolder */
        moneyMovementCategoryHolderService.addNewCostCategory(user, clientCostCategory);

        /** Create new money holder for test */
        moneyHolderService.create(user, "My test wallet");

        /** Create client account */
        accountService.create(user);

        /** Set MoneyMovementCategoryHolder for holding Earning and Cost clients categories*/
//        TODO refactor



        friendService.create(user, "Niko");

        budgetService.create(user, 10000L);

        notificationService.create(user, "Alarm, it's test notification!");

        goalService.create(user, 1000000L, "For my new car");
//        TODO end of test data

        transactionService.create(user.getAccount(),
                user.getMoneyHolder(1L),
                user.getMoneyHolder(1L), new BigDecimal("100"));

        portfolioService.create(user, "My Test Portfolio");

        /** Saving created client in DB */
        return userDao.save(user);
    }

    /**
     * Update existing client in system.
     * @param userFromDb - client data from ClientController which will be changed
     * @param userFromApi - current client from database on which to write new data and save again,
     *                     after validation and writing
     * @return - save updated client class in database and return it
     * */
    public User update(User userFromDb, User userFromApi){
        BeanUtils.copyProperties(userFromApi, userFromDb,
                "id",
                "registrationDate",
                "account",
                "moneyMovementCategoryHolder",
                "moneyHolderTypes",
                "friends",
                "budgets",
                "notifications",
                "goals");

        return userDao.save(userFromDb);
    }

    /**
     * Delete existing client account.
     * @param user - client which need to delete
     * */
    public void delete(User user){
        userDao.delete(user);
    }

    /**
     * Get and return all client from database.
     * */
    public List<User> getAll(){
        return userDao.findAll();
    }
}
