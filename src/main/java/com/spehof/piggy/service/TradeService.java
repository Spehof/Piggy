package com.spehof.piggy.service;

import com.spehof.piggy.DAO.TradeDao;
import com.spehof.piggy.domain.Asset;
import com.spehof.piggy.domain.BrokerSubAccount;
import com.spehof.piggy.domain.Trade;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@Service
public class TradeService {

    private final TradeDao tradeDao;

    @Autowired
    public TradeService(TradeDao tradeDao) {
        this.tradeDao = tradeDao;
    }

    public Trade create(BrokerSubAccount brokerSubAccount, Asset asset, Long amount){
        Trade trade = new Trade(brokerSubAccount, asset, amount);
        brokerSubAccount.setTrade(trade);
        return tradeDao.save(trade);
    }

    public List<Trade> getAll(BrokerSubAccount brokerSubAccount){
        return brokerSubAccount.getTrades();
    }

    public void create(BrokerSubAccount brokerSubAccount, Trade tradeFromApi){
        Trade tradeFromDb = brokerSubAccount.getTrade(tradeFromApi.getId());
        brokerSubAccount.removeTrade(tradeFromDb);
        tradeDao.delete(tradeFromDb);
    }

    public Trade update(BrokerSubAccount brokerSubAccount, Trade tradeFromApi){
        Trade tradeFromDb = brokerSubAccount.getTrade(tradeFromApi.getId());
        BeanUtils.copyProperties(tradeFromApi, tradeFromDb, "id", "brokerSubAccountWhichTrade");
        return tradeDao.save(tradeFromDb);
    }
}
