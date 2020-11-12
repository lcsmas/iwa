package com.ig5.iwa.repositories;

import com.ig5.iwa.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
