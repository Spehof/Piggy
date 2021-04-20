package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.BrokerSubAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 20/04/2021
 */
public interface BrokerSubAccountDao extends JpaRepository<BrokerSubAccount, Long> {
}
