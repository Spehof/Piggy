package com.spehof.piggy.controller;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Notification;
import com.spehof.piggy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Spehof
 * @created 14/04/2021
 */
@RestController("/api/v1/account/{id}/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> getAll(@PathVariable(name = "id")Account account){
        return account.getClient().getNotifications();
    }
}
