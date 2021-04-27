package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Spehof
 * @created 07/04/2021
 */
public interface UserDao extends JpaRepository<User, Long> {

    public Optional<User> getByName(String name);
}
