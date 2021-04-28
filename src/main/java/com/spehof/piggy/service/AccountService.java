package com.spehof.piggy.service;

import com.spehof.piggy.DAO.AccountDao;
import com.spehof.piggy.DAO.UserDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.User;
import com.spehof.piggy.service.moneymovement.CostService;
import com.spehof.piggy.service.moneymovement.EarningService;
import com.spehof.piggy.service.trading.BrokerService;
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
    private final UserDao userDao;
    private final EarningService earningService;
    private final CostService costService;
    private final BrokerService brokerService;

    @Autowired
    public AccountService(AccountDao accountDao,
                          UserDao userDao,
                          EarningService earningService,
                          CostService costService,
                          BrokerService brokerService) {

        this.accountDao = accountDao;
        this.userDao = userDao;
        this.earningService = earningService;
        this.costService = costService;
        this.brokerService = brokerService;
    }

    public Account create(User user){
        Account account = new Account(2);
        account.setUser(user);
        accountDao.save(account);
//        TODO for test!!
        for (long l : new long[]{124553L, 1234543L}) {
            costService.create(account, user.getMoneyMovementCategoryHolder().getCostCategory(1L), user.getMoneyHolder(1L), l);
        }
//        TODO for test!!
        for (long l : new long[]{22445L, 1265L}) {
            earningService.create(account, user.getMoneyMovementCategoryHolder().getEarningCategory(1L), user.getMoneyHolder(1L), l);
        }

        brokerService.create(account, "My broker name 1");
//        brokerService.create(account, "My broker name 2");
        return account;

    }

    public Account update(Account accountFromDb, Account accountFromApi){
        BeanUtils.copyProperties(accountFromApi, accountFromDb,
                "id",
                "user",
                "earnings",
                "costs",
                "brokers",
                "transactions"
        );
        return accountDao.save(accountFromDb);
    }

    public void delete(Account account){
        userDao.delete(account.getUser());
    }

    public List<Account> getAll(){
        return accountDao.findAll();
    }
}
