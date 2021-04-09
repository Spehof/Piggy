package com.spehof.piggy.service;

import com.spehof.piggy.DAO.MoneyMovementCategoryDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.MoneyMovementCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class MoneyMovementCategoryService {

    private final MoneyMovementCategoryDao moneyMovementCategoryDao;

    @Autowired
    public MoneyMovementCategoryService(MoneyMovementCategoryDao moneyMovementCategoryDao) {
        this.moneyMovementCategoryDao = moneyMovementCategoryDao;
    }

    public MoneyMovementCategory create(Client client, String name){
        MoneyMovementCategory moneyMovementCategory = new MoneyMovementCategory(client, name);
        return moneyMovementCategoryDao.save(moneyMovementCategory);
    }
}
