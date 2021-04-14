package com.spehof.piggy.service;

import com.spehof.piggy.DAO.CostCategoryDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.CostCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 13/04/2021
 *
 * Creating new unbinding entity "category" from name
 */

// TODO this class no need?
@Service
public class CostCategoryService {

    private final CostCategoryDao costCategoryDao;

    @Autowired
    public CostCategoryService(CostCategoryDao costCategoryDao) {
        this.costCategoryDao = costCategoryDao;
    }

    /** Create a new cost category from name
     * create (set it ID and binding with MoneyMovementCategoryHolder)
     * but created category in this method for now - not binding with no one client
     * just created entity with this category name
     *
     * @param client with MoneyMovementCategoryHolder will be binding this category
     * @param categoryName - name of category
     * */
    public CostCategory create(Client client, String categoryName){
        CostCategory costCategory = new CostCategory(client.getMoneyMovementCategoryHolder(), categoryName);
        return costCategoryDao.save(costCategory);
    }
}
