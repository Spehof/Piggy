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
    private final AssetService assetService;

    @Autowired
    public TradeService(TradeDao tradeDao,
                        AssetService assetService) {
        this.tradeDao = tradeDao;
        this.assetService = assetService;
    }

    /**
     * @param brokerSubAccount - broker sub account which buy assets
     * @param assetFromApi - name of asset
     * @param amount - amount asset trade
     * */
    public Trade create(BrokerSubAccount brokerSubAccount, Asset assetFromApi, Long amount){
        Asset assetFromDb = assetService.getAssetFromDb(assetFromApi.getName());
        Trade trade = new Trade(brokerSubAccount, assetFromDb, amount);
        brokerSubAccount.setTrade(trade);
        return tradeDao.save(trade);
    }

    public List<Trade> getAll(BrokerSubAccount brokerSubAccount){
        return brokerSubAccount.getTrades();
    }

    public void delete(BrokerSubAccount brokerSubAccount, Trade tradeFromApi){
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
