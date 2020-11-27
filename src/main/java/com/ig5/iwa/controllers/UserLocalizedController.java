package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.services.UserLocalizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/user_localized")
@RestController
public class UserLocalizedController {

    @Autowired
    public UserLocalizedService userLocalizedService;

    @GetMapping
    public List<User_Localized> list() {
        return userLocalizedService.findAll();
    }

    @GetMapping
    @RequestMapping("{id_user}/currentLoc")
    public Optional<Location> getCurrentLoc(@PathVariable int id_user) {
        if(userLocalizedService.noUserLocalizedByIdUserFound(id_user)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error : id_user " + id_user + " was not found");
        }else{
            return userLocalizedService.getCurrentLoc(id_user);
        }
    }

    @PostMapping(value = "add/{id_user}/longitude/{longitude}/latitude/{latitude}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public int addUserLocalized(@PathVariable int id_user, @PathVariable float longitude, @PathVariable float latitude) {
        if(userLocalizedService.noUserIdFound(id_user)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error : id_user " + id_user + " was not found");
        }
        return userLocalizedService.saveAndFlush(id_user,longitude,latitude);
    }

}
