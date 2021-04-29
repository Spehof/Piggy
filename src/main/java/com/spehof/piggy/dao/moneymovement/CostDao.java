package com.spehof.piggy.dao.moneymovement;

import com.spehof.piggy.domain.moneymovement.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 10/04/2021
 */
public interface CostDao extends JpaRepository<Cost, Long> {
}
