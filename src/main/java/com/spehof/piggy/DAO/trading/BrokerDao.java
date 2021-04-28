package com.spehof.piggy.DAO.trading;

import com.spehof.piggy.domain.trading.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 20/04/2021
 */
public interface BrokerDao extends JpaRepository<Broker, Long> {
}
