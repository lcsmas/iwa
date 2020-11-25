package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Notification;
import com.ig5.iwa.repositories.NotificationRepository;
import com.ig5.iwa.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@RequestMapping("api/v1/notifications")
@RestController
public class NotificationsController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> list() {
        return notificationService.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<Notification> get(@PathVariable Integer id) {
        if(notificationService.noNotificationIdFound(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_notification "+id+" not found");
        }else{
            return notificationService.findNotificationById(id);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@RequestBody final Notification notification) {
        return notificationService.create(notification);
    }
}
