package com.spehof.piggy.service;

import com.spehof.piggy.DAO.NotificationDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class NotificationService {

    private final NotificationDao notificationDao;

    @Autowired
    public NotificationService(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public Notification create(Client client, String message){
        Notification notification = new Notification(client, message);
        return notificationDao.save(notification);
    }
}
