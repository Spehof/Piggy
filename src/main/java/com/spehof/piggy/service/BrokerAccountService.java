package com.spehof.piggy.service;

import com.spehof.piggy.DAO.BrokerAccountDao;
import com.spehof.piggy.domain.Asset;
import com.spehof.piggy.domain.Broker;
import com.spehof.piggy.domain.BrokerAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@Service
public class BrokerAccountService {

    private final BrokerAccountDao brokerAccountDao;
    private final TradeService tradeService;
    private final AssetService assetService;

    @Autowired
    public BrokerAccountService(BrokerAccountDao brokerAccountDao,
                                TradeService tradeService,
                                AssetService assetService) {
        this.brokerAccountDao = brokerAccountDao;
        this.tradeService = tradeService;
        this.assetService = assetService;
    }

    public BrokerAccount create(Broker broker, String title){
        BrokerAccount brokerAccount = new BrokerAccount(broker, title);
        broker.setBrokerAccount(brokerAccount);
        brokerAccountDao.save(brokerAccount);

//        TODO data for test
        Asset testAsset = assetService.create("SBER", "250");
        tradeService.create(brokerAccount, testAsset, 1500L);
        return  brokerAccountDao.save(brokerAccount);

    }

    public List<BrokerAccount> getAll(Broker broker){
        return broker.getBrokerAccounts();
    }

    public BrokerAccount getOne(Broker broker, BrokerAccount brokerAccountFromApi){
        return broker.getBrokerAccount(brokerAccountFromApi.getId());
    }

    public void delete(Broker broker, BrokerAccount brokerAccount){
        broker.removeBrokerSubAccount(brokerAccount);
        brokerAccountDao.delete(brokerAccount);
    }

    public BrokerAccount update(Broker broker, BrokerAccount brokerAccountFromApi){
        BrokerAccount brokerAccountFromDb = broker.getBrokerAccount(brokerAccountFromApi.getId());
        if (brokerAccountFromApi.getAssets() != null) {
            BeanUtils.copyProperties(brokerAccountFromApi, brokerAccountFromDb,
                    "id",
                    "broker");
        } else {
            BeanUtils.copyProperties(brokerAccountFromApi, brokerAccountFromDb,
                    "id",
                    "broker",
                    "assets");
        }
        return brokerAccountDao.save(brokerAccountFromDb);
    }
}
