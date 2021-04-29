package com.spehof.piggy.dao.category;

import com.spehof.piggy.domain.category.MoneyMovementCategoryHolder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 10/04/2021
 */
public interface MoneyMovementCategoryHolderDao extends JpaRepository<MoneyMovementCategoryHolder, Long> {
}
