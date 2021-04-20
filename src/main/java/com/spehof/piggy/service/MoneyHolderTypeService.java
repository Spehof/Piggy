package com.spehof.piggy.service;

import com.spehof.piggy.DAO.MoneyHolderTypeDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.MoneyHolderType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<MoneyHolderType> getAll(Client client) {
        return client.getMoneyHolderTypes();
    }

    public MoneyHolderType getOne(Client client, Long moneyHolderTypeId) {
        return client.getMoneyHolderType(moneyHolderTypeId);
    }

    public void delete(Client client, MoneyHolderType moneyHolderTypeFromApi) {
        client.removeMoneyHolderType(moneyHolderTypeFromApi);
        moneyHolderTypeDao.delete(moneyHolderTypeFromApi);
    }

    public MoneyHolderType update(Client client, MoneyHolderType moneyHolderTypeFromApi) {
        MoneyHolderType moneyHolderTypeFromDb = client.getMoneyHolderType(moneyHolderTypeFromApi.getId());
        BeanUtils.copyProperties(moneyHolderTypeFromApi, moneyHolderTypeFromDb, "id", "client");
        return moneyHolderTypeDao.save(moneyHolderTypeFromDb);
    }
}
