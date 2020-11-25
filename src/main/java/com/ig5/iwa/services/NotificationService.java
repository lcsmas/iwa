package com.ig5.iwa.services;


import com.ig5.iwa.models.Notification;
import com.ig5.iwa.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    public NotificationRepository notificationRepository;

    public Boolean noNotificationIdFound(int id){
        return notificationRepository.findById(id).isEmpty();
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    public Notification create(Notification notification) {
        return notificationRepository.saveAndFlush(notification);
    }
}
