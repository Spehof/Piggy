package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 10/04/2021
 */
public interface FriendDao extends JpaRepository<Friend, Long> {
}
