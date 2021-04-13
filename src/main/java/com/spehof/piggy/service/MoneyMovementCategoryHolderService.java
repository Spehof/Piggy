package com.spehof.piggy.service;

import com.spehof.piggy.DAO.MoneyMovementCategoryDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.MoneyMovementCategoryHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class MoneyMovementCategoryHolderService {

    private final MoneyMovementCategoryDao moneyMovementCategoryDao;

    @Autowired
    public MoneyMovementCategoryHolderService(MoneyMovementCategoryDao moneyMovementCategoryDao) {
        this.moneyMovementCategoryDao = moneyMovementCategoryDao;
    }

    public MoneyMovementCategoryHolder create(Client client){
        MoneyMovementCategoryHolder moneyMovementCategoryHolder = new MoneyMovementCategoryHolder(client);
        return moneyMovementCategoryDao.save(moneyMovementCategoryHolder);
    }
}
