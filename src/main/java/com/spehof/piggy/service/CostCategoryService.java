package com.spehof.piggy.service;

import com.spehof.piggy.DAO.CostCategoryDao;
import com.spehof.piggy.domain.CostCategory;
import com.spehof.piggy.domain.MoneyMovementCategoryHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 13/04/2021
 *
 * Class for manage client cost category
 */
@Service
public class CostCategoryService {

    private final CostCategoryDao costCategoryDao;

    @Autowired
    public CostCategoryService(CostCategoryDao costCategoryDao) {
        this.costCategoryDao = costCategoryDao;
    }

    /** Create a new cost category*/
    public CostCategory create(MoneyMovementCategoryHolder moneyMovementCategoryHolder, String name){
        CostCategory costCategory = new CostCategory(moneyMovementCategoryHolder, name);
        return costCategoryDao.save(costCategory);
    }

    public void remove(MoneyMovementCategoryHolder moneyMovementCategoryHolder, String name){
// TODO write
    }
}
