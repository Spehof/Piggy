package com.spehof.piggy.dao.moneymovement;

import com.spehof.piggy.domain.moneymovement.Earning;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 09/04/2021
 */
public interface EarningDao extends JpaRepository<Earning, Long> {
}
