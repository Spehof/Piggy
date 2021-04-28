package com.spehof.piggy.DAO.moneymovement;

import com.spehof.piggy.domain.moneymovement.MoneyHolder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 21/04/2021
 */
public interface MoneyHolderDao extends JpaRepository<MoneyHolder, Long> {
}
