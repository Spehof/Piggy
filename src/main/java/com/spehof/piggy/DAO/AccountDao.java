package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Spehof
 * @created 09/04/2021
 */
public interface AccountDao extends JpaRepository<Account, Long> {


}
