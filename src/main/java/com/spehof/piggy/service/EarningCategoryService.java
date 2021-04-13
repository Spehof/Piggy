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
@Service
public class EarningCategoryService {

    private final EarningCategoryDao earningCategoryDao;

    @Autowired
    public EarningCategoryService(EarningCategoryDao earningCategoryDao) {
        this.earningCategoryDao = earningCategoryDao;
    }

    /** Create a new earning category
     * @param client
     * @param categoryName*/
    public EarningCategory create(Client client, String categoryName){
        EarningCategory earningCategory = new EarningCategory(client.getMoneyMovementCategoryHolder(), categoryName);
        return earningCategoryDao.save(earningCategory);
    }

    public void remove(Client client, String categoryName){
        EarningCategory earningCategoryForDelete = client.getMoneyMovementCategoryHolder().getEarningCategories()
                .stream()
                .filter(earningCategory -> earningCategory.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(EarningCategoryNotFoundException::new);

        earningCategoryDao.delete(earningCategoryForDelete);
    }
// TODO refactor signature
    public Set<EarningCategory> getAll(Account account){
        return account.getClient().getMoneyMovementCategoryHolder().getEarningCategories();
    }
}
