package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Spehof
 * @created 07/04/2021
 */
public interface UserDao extends JpaRepository<Client, Long> {

    public Optional<Client> getByName(String name);
}
