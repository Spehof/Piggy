package com.spehof.piggy.service;

import com.spehof.piggy.DAO.MoneyHolderDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.MoneyHolder;
import com.spehof.piggy.domain.MoneyHolderType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@Service
public class MoneyHolderService {

    private final MoneyHolderDao moneyHolderDao;

    @Autowired
    public MoneyHolderService(MoneyHolderDao moneyHolderDao) {
        this.moneyHolderDao = moneyHolderDao;
    }

    public MoneyHolder create(Client client, MoneyHolderType moneyHolderType, String title){
        MoneyHolder moneyHolder = new MoneyHolder(client, moneyHolderType, title);
        client.setMoneyHolder(moneyHolder);
        return moneyHolderDao.save(moneyHolder);
    }

    public List<MoneyHolder> getAll(Client client){
        return client.getMoneyHolders();
    }

    public void delete(Client client, MoneyHolder moneyHolderFromApi){
        MoneyHolder moneyHolderFromDb = client.getMoneyHolder(moneyHolderFromApi.getId());
        client.removeMoneyHolder(moneyHolderFromDb);
        moneyHolderDao.delete(moneyHolderFromDb);
    }

    public MoneyHolder update(Client client, MoneyHolder moneyHolderFromApi){
        MoneyHolder moneyHolderFromDb = client.getMoneyHolder(moneyHolderFromApi.getId());
        if (moneyHolderFromApi.getMoneyHolderType() == null){
            BeanUtils.copyProperties(moneyHolderFromApi, moneyHolderFromDb, "id", "client", "moneyHolderType");
        } else {
            BeanUtils.copyProperties(moneyHolderFromApi, moneyHolderFromDb, "id", "client");
        }
        return moneyHolderDao.save(moneyHolderFromDb);
    }
}
