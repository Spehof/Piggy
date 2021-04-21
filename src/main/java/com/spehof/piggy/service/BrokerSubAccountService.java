package com.spehof.piggy.service;

import com.spehof.piggy.DAO.BrokerSubAccountDao;
import com.spehof.piggy.domain.Asset;
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
public class BrokerSubAccountService {

    private final BrokerSubAccountDao brokerSubAccountDao;
    private final TradeService tradeService;
    private final AssetService assetService;

    @Autowired
    public BrokerSubAccountService(BrokerSubAccountDao brokerSubAccountDao,
                                   TradeService tradeService,
                                   AssetService assetService) {
        this.brokerSubAccountDao = brokerSubAccountDao;
        this.tradeService = tradeService;
        this.assetService = assetService;
    }

    public BrokerSubAccount create(Broker broker, String title){
        BrokerSubAccount brokerSubAccount = new BrokerSubAccount(broker, title);
        broker.setBrokerSubAccount(brokerSubAccount);
        brokerSubAccountDao.save(brokerSubAccount);

//        TODO data for test
        Asset testAsset = assetService.create("SBER", "250");
        tradeService.create(brokerSubAccount, testAsset, 1500L);
        return  brokerSubAccountDao.save(brokerSubAccount);

    }

    public List<BrokerSubAccount> getAll(Broker broker){
        return broker.getBrokerSubAccounts();
    }

    public BrokerSubAccount getOne(Broker broker, BrokerSubAccount brokerSubAccountFromApi){
        return broker.getBrokerSubAccount(brokerSubAccountFromApi.getId());
    }

    public void delete(Broker broker, BrokerSubAccount brokerSubAccount){
        broker.removeBrokerSubAccount(brokerSubAccount);
        brokerSubAccountDao.delete(brokerSubAccount);
    }

    public BrokerSubAccount update(Broker broker, BrokerSubAccount brokerSubAccountFromApi){
        BrokerSubAccount brokerSubAccountFromDb = broker.getBrokerSubAccount(brokerSubAccountFromApi.getId());
        BeanUtils.copyProperties(brokerSubAccountFromApi, brokerSubAccountFromDb, "id", "broker");
        return brokerSubAccountDao.save(brokerSubAccountFromDb);
    }
}
