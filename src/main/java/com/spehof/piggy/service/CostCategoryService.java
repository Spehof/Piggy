package com.spehof.piggy.service;

import com.spehof.piggy.DAO.CostCategoryDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.CostCategory;
import com.spehof.piggy.exception.CostCategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Spehof
 * @created 13/04/2021
 *
 * Class for manage client cost category
 */

// TODO this class no need?
@Service
public class CostCategoryService {

    private final CostCategoryDao costCategoryDao;

    @Autowired
    public CostCategoryService(CostCategoryDao costCategoryDao) {
        this.costCategoryDao = costCategoryDao;
    }

    /** Create a new cost category
     * @param client
     * @param categoryName */
    public CostCategory create(Client client, String categoryName){
        CostCategory costCategory = new CostCategory(client.getMoneyMovementCategoryHolder(), categoryName);
        return costCategoryDao.save(costCategory);
    }

    public void remove(Client client, String categoryName){
        CostCategory costCategoryForDelete = client.getMoneyMovementCategoryHolder().getCostCategories()
                .stream()
                .filter(costCategory -> costCategory.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(CostCategoryNotFoundException::new);

        costCategoryDao.delete(costCategoryForDelete);
    }

//    TODO refactor signature
    public Set<CostCategory> getAll(Account account){
        return account.getClient().getMoneyMovementCategoryHolder().getCostCategories();
    }
}
