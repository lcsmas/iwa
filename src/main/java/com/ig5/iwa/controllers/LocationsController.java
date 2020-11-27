package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/locations")
@RestController
public class LocationsController {

    @Autowired
    public LocationService locationService;

    @GetMapping
    public List<Location> list() {
        System.out.println("Get all Locations");
        return locationService.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<Location> get(@PathVariable Integer id) {
        if(locationService.noLocationIdFound(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_location " + id + " not found");
        }else{
            return locationService.findLocationById(id);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody float longitude, float latitude, User_Localized currentUser) {
        return locationService.create(longitude, latitude, currentUser);
    }

    @RequestMapping(path= "exists", method = RequestMethod.POST)
    public Boolean exists(@RequestBody Location location){
        return locationService.existsLocationWithLoc(location);
    }
}
