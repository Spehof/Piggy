package com.spehof.piggy.dao.trading;

import com.spehof.piggy.domain.trading.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 20/04/2021
 */
public interface PortfolioDao extends JpaRepository<Portfolio, Long> {
}
