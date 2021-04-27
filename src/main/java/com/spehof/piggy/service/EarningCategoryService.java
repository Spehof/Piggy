package com.spehof.piggy.service;

import com.spehof.piggy.DAO.EarningCategoryDao;
import com.spehof.piggy.domain.User;
import com.spehof.piggy.domain.EarningCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 13/04/2021
 *
 * Creating new unbinding entity "category" from name
 */
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
     * @param user with MoneyMovementCategoryHolder will be binding this category
     * @param categoryName - name of category
     * */
    public EarningCategory create(User user, String categoryName){
        EarningCategory earningCategory = new EarningCategory(user.getMoneyMovementCategoryHolder(), categoryName);
        return earningCategoryDao.save(earningCategory);
    }
}
