package com.spehof.piggy.dao;

import com.spehof.piggy.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 10/04/2021
 */
public interface GoalDao extends JpaRepository<Goal, Long> {
}
