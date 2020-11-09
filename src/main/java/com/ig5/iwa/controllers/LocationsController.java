package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Location;
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
        return locationRepository.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<Location> get(@PathVariable Integer id) {
        return locationRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody float longitude, float latitude) {
        Location sendNewLoc = new Location(longitude,latitude);
        return locationRepository.saveAndFlush(sendNewLoc);
    }
}
