package com.ig5.iwa.repositories;

import com.ig5.iwa.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAllByUser_IdUserOrderByDateNotificationDesc(int id_user);
}
