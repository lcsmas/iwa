package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User;
import com.ig5.iwa.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/locations")
@RestController
public class LocationsController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> list() {
        System.out.println("Get all Locations");
        return locationRepository.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<Location> get(@PathVariable Integer id) {
        System.out.println("Get Location "+id);
        return locationRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody float longitude, float latitude, User currentUser) {
        Location sendNewLoc = new Location(longitude,latitude);
        List<User> newList = sendNewLoc.getUsers();
        newList.add(currentUser);
        sendNewLoc.setUsers(newList);
        System.out.println("Post Location:\n-long:"+sendNewLoc.getLongitude()
                +"\n-lat:"+sendNewLoc.getLatitude()
                +"\n-users_localized:"+sendNewLoc.getUsers());
        return locationRepository.saveAndFlush(sendNewLoc);
    }
}
