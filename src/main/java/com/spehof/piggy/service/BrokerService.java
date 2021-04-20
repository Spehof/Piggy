package com.spehof.piggy.service;

import com.spehof.piggy.DAO.BrokerDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Broker;
import com.spehof.piggy.domain.BrokerSubAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@Service
public class BrokerService {

    private final BrokerDao brokerDao;
    private final BrokerSubAccountService brokerSubAccountService;

    @Autowired
    public BrokerService(BrokerDao brokerDao,
                         BrokerSubAccountService brokerSubAccountService) {
        this.brokerDao = brokerDao;
        this.brokerSubAccountService = brokerSubAccountService;
    }

    public Broker create(Account account, String brokerName){
        Broker broker = new Broker(account, brokerName);
        account.setBroker(broker);
        brokerDao.save(broker);

        BrokerSubAccount technicalBrokerSubAccount = brokerSubAccountService.create(broker, "technical");
        return brokerDao.save(broker);
    }

    public List<Broker> getAll(Account account){
        return account.getBrokers();
    }

    public Broker getOne(Account account, Broker broker){
        return account.getBroker(broker.getId());
    }

    public void delete(Account account, Broker brokerFromApi){
        account.removeBroker(brokerFromApi);
        brokerDao.delete(brokerFromApi);
    }

    public Broker update(Account account, Broker brokerFromApi){
        Broker brokerFromDb = account.getBroker(brokerFromApi.getId());
        BeanUtils.copyProperties(brokerFromApi, brokerFromDb, "id", "account");
        return brokerDao.save(brokerFromDb);
    }
}
