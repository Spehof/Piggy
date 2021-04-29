package com.spehof.piggy.dao.category;

import com.spehof.piggy.domain.category.EarningCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 13/04/2021
 */
public interface EarningCategoryDao extends JpaRepository<EarningCategory, Long> {
}
