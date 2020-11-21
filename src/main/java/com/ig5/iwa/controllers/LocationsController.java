package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("api/v1/locations")
@RestController
public class LocationsController {
    @Autowired
    public LocationRepository locationRepository;

    @GetMapping
    public List<Location> list() {
        System.out.println("Get all Locations");
        return locationRepository.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<Location> get(@PathVariable Integer id) {
        if(locationRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_location "+id+" not found");
        }else{
            return locationRepository.findById(id);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody float longitude, float latitude, User_Localized currentUser) {
        Location sendNewLoc = new Location(longitude,latitude);
        Set<User_Localized> newList = sendNewLoc.getUsers();
        newList.add(currentUser);
        sendNewLoc.setUsers(newList);
        System.out.println("Post Location:\n-long:"+sendNewLoc.getLongitude()
                +"\n-lat:"+sendNewLoc.getLatitude()
                +"\n-users_localized:"+sendNewLoc.getUsers());
        return locationRepository.saveAndFlush(sendNewLoc);
    }

}
