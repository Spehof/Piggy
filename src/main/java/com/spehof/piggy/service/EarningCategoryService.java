package com.spehof.piggy.service;

import com.spehof.piggy.DAO.EarningCategoryDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.EarningCategory;
import com.spehof.piggy.exception.EarningCategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Spehof
 * @created 13/04/2021
 *
 * Class for manage client earning category
 */
// TODO this class no need?
@Service
public class EarningCategoryService {

    private final EarningCategoryDao earningCategoryDao;

    @Autowired
    public EarningCategoryService(EarningCategoryDao earningCategoryDao) {
        this.earningCategoryDao = earningCategoryDao;
    }

    /** Create a new earning category from name
     * create (set it ID and binding with MoneyMovementCategoryHolder)
     * but created category in this method for now - not binding with no one client
     * just created entity with this category name
     *
     * @param client with MoneyMovementCategoryHolder will be binding this category
     * @param categoryName - name of category
     * */
    public EarningCategory create(Client client, String categoryName){
        EarningCategory earningCategory = new EarningCategory(client.getMoneyMovementCategoryHolder(), categoryName);
        return earningCategoryDao.save(earningCategory);
    }
}
