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
@RequestMapping("/api/v1/account/{id}/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> getAll(@PathVariable(name = "id")Account account){
        return notificationService.getAll(account.getClient());
    }

    @PostMapping
    public Notification createNewNotification(@PathVariable(name = "id")Account account,
                                              @RequestBody Notification notification){
        return notificationService.create(account.getClient(), notification.getMessage());
    }

    @DeleteMapping()
    public void deleteNotification(@PathVariable(name = "id") Account account,
                                   @RequestBody Notification notification){
        notificationService.delete(account.getClient(), notification);
    }

    //    TODO deleting across ID or something else
    @PutMapping()
    public Notification updateNotification(@PathVariable(name = "id") Account account,
                                   @RequestBody Notification newNotification){
//        TODO refactor: create a sending new notification if all right
        return notificationService.update(account.getClient(), newNotification);
    }
}
