package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Notification;
import com.ig5.iwa.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("api/v1/notifications")
@RestController
public class NotificationsController {
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> list() {
        return notificationRepository.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<Notification> get(@PathVariable Integer id) {
        return notificationRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@RequestBody final Notification notification) {
        return notificationRepository.saveAndFlush(notification);
    }
}
