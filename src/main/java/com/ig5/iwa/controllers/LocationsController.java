package com.ig5.iwa.controllers;

import com.ig5.iwa.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/locations")
@RestController
public class LocationsController {
    @Autowired
    private LocationRepository locationRepository;
}
