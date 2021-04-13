package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.Cost;
import com.spehof.piggy.domain.CostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 13/04/2021
 */
public interface CostCategoryDao extends JpaRepository<CostCategory, Long> {
}
