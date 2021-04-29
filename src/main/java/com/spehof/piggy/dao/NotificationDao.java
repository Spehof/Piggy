package com.spehof.piggy.dao;

import com.spehof.piggy.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 10/04/2021
 */
public interface NotificationDao extends JpaRepository<Notification, Long> {
}
