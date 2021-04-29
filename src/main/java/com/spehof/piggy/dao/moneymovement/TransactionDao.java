package com.spehof.piggy.dao.moneymovement;

import com.spehof.piggy.domain.moneymovement.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 21/04/2021
 */
public interface TransactionDao extends JpaRepository<Transaction, Long> {
}
