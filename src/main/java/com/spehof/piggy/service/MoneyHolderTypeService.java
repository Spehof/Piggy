package com.spehof.piggy.service;

import com.spehof.piggy.DAO.MoneyHolderTypeDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.MoneyHolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class MoneyHolderTypeService {

    private final MoneyHolderTypeDao moneyHolderTypeDao;

    @Autowired
    public MoneyHolderTypeService(MoneyHolderTypeDao moneyHolderTypeDao) {
        this.moneyHolderTypeDao = moneyHolderTypeDao;
    }

    public MoneyHolderType create(Client client, String name){
        MoneyHolderType moneyHolderType = new MoneyHolderType(client, name);
        client.setMoneyHolderType(moneyHolderType);
        return moneyHolderTypeDao.save(moneyHolderType);
    }
}
