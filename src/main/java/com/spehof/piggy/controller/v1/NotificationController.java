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
                                              @RequestBody Notification notificationFromApi){
        return notificationService.create(account.getClient(), notificationFromApi.getMessage());
    }

    @DeleteMapping()
    public void deleteNotification(@PathVariable(name = "id") Account account,
                                   @RequestBody Notification notificationFromApi){
        notificationService.delete(account.getClient(), notificationFromApi);
    }

    @PutMapping()
    public Notification updateNotification(@PathVariable(name = "id") Account account,
                                           @RequestBody Notification notificationFromApi){
        return notificationService.update(account.getClient(), notificationFromApi);
    }
}
