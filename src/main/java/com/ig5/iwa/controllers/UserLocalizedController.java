package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.repositories.LocationRepository;
import com.ig5.iwa.repositories.UserLocalizedRepository;
import com.ig5.iwa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequestMapping("api/v1/user_localized")
@RestController
public class UserLocalizedController {
    @Autowired
    public UserLocalizedRepository userLocalizedRepository;

    @Autowired
    public LocationRepository locationRepository;

    @Autowired
    public UserRepository userRepository;

    @GetMapping
    public List<User_Localized> list() {
        System.out.println("get all users");
        return userLocalizedRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id_user}/currentState")
    public Optional<Location> get(@PathVariable int id_user) {
        if(userLocalizedRepository.findTopById_IdUserOrderByDateDesc(id_user).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error : id_user " + id_user + " was not found");
        }else{
            Optional<Location> currentLoc = userLocalizedRepository.findTopById_IdUserOrderByDateDesc(id_user)
                    .map(User_Localized::getLocation);
            return currentLoc;
        }
    }

    @PostMapping(value = "add/{id_user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User addUserLocalized(@PathVariable int id_user, @RequestBody Location data) {
        if(userRepository.findById(id_user).isPresent()){
            User u = userRepository.findById(id_user).orElse(new User());
            Location location = new Location(data.getLongitude(),data.getLatitude());
            Location l = locationRepository.save(location);
            System.out.println("------------  loc "+l.getId_Location());
            User_Localized ul = new User_Localized(u,l);
            u.addUserLocation(ul);
            return userRepository.saveAndFlush(u);
        }
        return null;
    }

}
