package com.spehof.piggy.DAO.category;

import com.spehof.piggy.domain.category.CostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 13/04/2021
 */
public interface CostCategoryDao extends JpaRepository<CostCategory, Long> {
}
