package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.MoneyMovementCategoryHolder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 10/04/2021
 */
public interface MoneyMovementCategoryDao extends JpaRepository<MoneyMovementCategoryHolder, Long> {
}
