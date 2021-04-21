package com.spehof.piggy.service;

import com.spehof.piggy.DAO.AccountDao;
import com.spehof.piggy.DAO.ClientDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Service
public class AccountService {

    private final AccountDao accountDao;
    private final ClientDao clientDao;
    private final EarningService earningService;
    private final CostService costService;
    private final BrokerService brokerService;

    @Autowired
    public AccountService(AccountDao accountDao,
                          ClientDao clientDao,
                          EarningService earningService,
                          CostService costService,
                          BrokerService brokerService) {

        this.accountDao = accountDao;
        this.clientDao = clientDao;
        this.earningService = earningService;
        this.costService = costService;
        this.brokerService = brokerService;
    }

    public void create(Client client){
        Account account = new Account(2);
        account.setClient(client);
        accountDao.save(account);
//        TODO for test!!
        for (long l : new long[]{124553L, 1234543L}) {
            costService.create(account, l);
        }
//        TODO for test!!
        for (long l : new long[]{22445L, 1265L}) {
            earningService.create(account, l);
        }

        brokerService.create(account, "My broker name 1");
//        brokerService.create(account, "My broker name 2");

    }

    public Account update(Account accountFromDb, Account accountFromApi){
        BeanUtils.copyProperties(accountFromApi, accountFromDb,
                "id",
                "client",
                "earnings",
                "costs");
        return accountDao.save(accountFromDb);
    }

    public void delete(Account account){
        clientDao.delete(account.getClient());
    }

    public List<Account> getAll(){
        return accountDao.findAll();
    }
}
