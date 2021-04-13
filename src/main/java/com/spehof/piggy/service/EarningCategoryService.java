package com.spehof.piggy.service;

import com.spehof.piggy.DAO.EarningCategoryDao;
import com.spehof.piggy.domain.EarningCategory;
import com.spehof.piggy.domain.MoneyMovementCategoryHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 13/04/2021
 *
 * Class for manage client earning category
 */
@Service
public class EarningCategoryService {

    private final EarningCategoryDao earningCategoryDao;

    @Autowired
    public EarningCategoryService(EarningCategoryDao earningCategoryDao) {
        this.earningCategoryDao = earningCategoryDao;
    }

    /** Create a new earning category*/
    public EarningCategory create(MoneyMovementCategoryHolder moneyMovementCategoryHolder, String name){
        EarningCategory earningCategory = new EarningCategory(moneyMovementCategoryHolder, name);
        return earningCategoryDao.save(earningCategory);
    }

    public void remove(MoneyMovementCategoryHolder moneyMovementCategoryHolder, String name){
        // TODO write
    }
}
