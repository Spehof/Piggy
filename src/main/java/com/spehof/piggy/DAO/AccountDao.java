package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 09/04/2021
 */
public interface AccountDao extends JpaRepository<Account, Long> {
}
