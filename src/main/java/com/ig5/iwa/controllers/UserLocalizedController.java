package com.ig5.iwa.controllers;

import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.repositories.UserLocalizedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/user_localized")
@RestController
public class UserLocalizedController {
    @Autowired
    public UserLocalizedRepository userLocalizedRepository;


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
}
