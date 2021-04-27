package com.spehof.piggy.service;

import com.spehof.piggy.DAO.NotificationDao;
import com.spehof.piggy.domain.User;
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

    public Notification create(User user, String message){
        Notification notification = new Notification(user, message);
        user.setNotification(notification);
        return notificationDao.save(notification);
    }

    public List<Notification> getAll(User user){
        return user.getNotifications();
    }

    public void delete(User user, Notification notificationFromApi){
        user.removeNotification(notificationFromApi);
        notificationDao.delete(notificationFromApi);
    }

    public Notification update(User user,
                               Notification notificationFromApi){
        Notification notificationFromDb = user.getNotification(notificationFromApi.getId());
        BeanUtils.copyProperties(notificationFromApi, notificationFromDb, "id", "user");
        return notificationDao.save(notificationFromDb);
    }
}
