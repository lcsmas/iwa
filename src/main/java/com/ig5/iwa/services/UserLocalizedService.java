package com.ig5.iwa.services;


import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.State;
import com.ig5.iwa.models.User;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.repositories.StateRepository;
import com.ig5.iwa.repositories.UserLocalizedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserLocalizedService {

    @Autowired
    public UserLocalizedRepository userLocalizedRepository;

    @Autowired
    public UserService userService;

    @Autowired
    public LocationService locationService;

    public Boolean noUserLocalizedByIdUserFound(int id_user){
        return userLocalizedRepository.findTopById_IdUserOrderByDateDesc(id_user).isEmpty();
    }

    public List<User_Localized> findAll() {
        return userLocalizedRepository.findAll();
    }

    public Optional<Location> getCurrentLoc(int id_user){
        return userLocalizedRepository.findTopById_IdUserOrderByDateDesc(id_user)
                .map(User_Localized::getLocation);
    }

    public Boolean noUserIdFound(int id){
        return userService.noUserIdFound(id);
    }

    public int saveAndFlush(int id_user, float longitude ,float latitude) {
        User u = userService.findUserById(id_user).orElse(new User());
        Location location = new Location(longitude,latitude);
        Location l = locationService.save(location);
        System.out.println("------------  loc "+l.getId_Location());
        User_Localized ul = new User_Localized(u,l);
        u.addUserLocation(ul);
        userService.saveAndFlush(u);
        return l.getId_Location();
    }
}