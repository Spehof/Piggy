package com.spehof.piggy.service;

import com.spehof.piggy.DAO.NotificationDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.Notification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        client.setNotification(notification);
        return notificationDao.save(notification);
    }

    public List<Notification> getAll(Client client){
        return client.getNotifications();
    }

    public void delete(Client client, Notification notificationFromApi){
        client.removeNotification(notificationFromApi);
        notificationDao.delete(notificationFromApi);
    }

    public Notification update(Client client,
                               Notification notificationFromApi){
        Notification notificationFromDb = client.getNotification(notificationFromApi.getId());
        BeanUtils.copyProperties(notificationFromApi, notificationFromDb, "id", "client");
        return notificationDao.save(notificationFromDb);
    }
}
