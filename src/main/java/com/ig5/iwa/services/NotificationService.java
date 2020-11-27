package com.ig5.iwa.services;


import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.Notification;
import com.ig5.iwa.models.State;
import com.ig5.iwa.models.User;
import com.ig5.iwa.repositories.LocationRepository;
import com.ig5.iwa.repositories.NotificationRepository;
import com.ig5.iwa.repositories.StateRepository;
import com.ig5.iwa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    public NotificationRepository notificationRepository;

    @Autowired
    public UserService userService;

    @Autowired
    public LocationService locationService;

    @Autowired
    public StateRepository stateRepository;

    @Autowired
    public LocationRepository locationRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserLocalizedService userLocalizedService;

    @Autowired
    public StateService stateService;

    public Boolean noNotificationIdFound(int id){
        return notificationRepository.findById(id).isEmpty();
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Boolean noUserIdFound(int id){
        return userService.noUserIdFound(id);
    }

    public Boolean noStateIdFound(int id){
        return stateService.noStateIdFound(id);
    }

    public Boolean noLocationIdFound(int id){
        return locationService.noLocationIdFound(id);
    }

    public Optional<Notification> findNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    public Notification createNot(int id_user, int id_state , int id_location , String label_state) {
        Notification notification = new Notification(label_state);
        State state = stateRepository.getOne(id_state);
        System.out.println("#-#-#-#-#-# " +state.getId_state());
        User user = userRepository.getOne(id_user);
        System.out.println("#-#-#-#-#-# " +user.getId_user());
        Location location = locationRepository.getOne(id_location);
        System.out.println("#-#-#-#-#-# " +location.getId_Location());
        notification.setUser(user);
        notification.setState(state);
        notification.setLocation(location);
        return notificationRepository.saveAndFlush(notification);
    }

    public List<Notification> findNotificationsByUserId(Integer id) {
        return notificationRepository.findAllByUser_IdUserOrderByDateNotificationDesc(id);
    };

    public Notification persistKafkaNotif(int idUserCovid,int idCurrentUser , int idState , float longCovid, float latCovid){
        int idLocationCovid = userLocalizedService.save(idUserCovid,longCovid,latCovid);
        return createNot(idCurrentUser,idState,idLocationCovid,"Contact avec une personne malade");
    }
}
