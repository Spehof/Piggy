package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.EarningCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 13/04/2021
 */
public interface EarningCategoryDao extends JpaRepository<EarningCategory, Long> {
}
