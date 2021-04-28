package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.trading.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 21/04/2021
 */
public interface TradeDao extends JpaRepository<Trade, Long> {
}
