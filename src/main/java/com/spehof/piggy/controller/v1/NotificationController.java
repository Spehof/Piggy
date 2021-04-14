package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Notification;
import com.spehof.piggy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 14/04/2021
 */
@RestController()
@RequestMapping("/api/v1/account/{id}/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> getAll(@PathVariable(name = "id")Account account){
//        TODO move to service
        return account.getClient().getNotifications();
    }

    @PostMapping
    public void createNewNotification(@PathVariable(name = "id")Account account,
                                      @RequestBody Notification notification){
//        TODO save new in service method
        Notification clientNotification = notificationService.create(account.getClient(), notification.getMessage());
        account.getClient().setNotification(clientNotification);
    }
}
