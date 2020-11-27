package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Notification;
import com.ig5.iwa.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @RequestMapping ("user/{id}")
    public List<Notification> getByUser(@PathVariable Integer id) {
            return notificationService.findNotificationsByUserId(id);
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


    @PostMapping(value = "user/{id_user}/state/{id_state}/location/{id_location}/label/{label_notification}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Notification createNotif(@PathVariable Integer id_user, @PathVariable Integer id_state , @PathVariable Integer id_location , @PathVariable String label_notification ) {
        if (notificationService.noUserIdFound(id_user)) {
            System.out.println("1");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_user " + id_user + " not found");
        } else if (notificationService.noStateIdFound(id_state)) {
            System.out.println("2");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_state" + id_state + " not found");
        } else if (notificationService.noLocationIdFound(id_location)) {
            System.out.println("3");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_location " + id_location + " not found");
        }
        else{
            return notificationService.createNot(id_user, id_state, id_location, label_notification);
        }
    }
/*
    @PostMapping(value = "/kafka")
    @ResponseStatus(HttpStatus.CREATED)
    public Notification persistKafka(){
        return notificationService.persistKafkaNotif();
    }
 */
}
