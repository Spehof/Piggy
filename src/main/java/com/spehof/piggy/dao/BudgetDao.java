package com.spehof.piggy.dao;

import com.spehof.piggy.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 10/04/2021
 */
public interface BudgetDao extends JpaRepository<Budget, Long> {
}
