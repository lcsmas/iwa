package com.ig5.iwa.controllers;

import com.ig5.iwa.models.*;
import com.ig5.iwa.services.UserStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/user_states")
@RestController
public class UserStatesController {
    @Autowired
    public UserStateService userStateService;

    @GetMapping
    public List<User_State> list() {
        return userStateService.findAll();
    }

    @GetMapping
    @RequestMapping("{id_user}")
    public List<User_State> listById(@PathVariable int id_user) {
        return userStateService.listById(id_user);
    }

    @GetMapping
    @RequestMapping("{id_user}/currentState")
    public Optional<String> get(@PathVariable int id_user) {
        if(userStateService.findOneUserStateWithUserId(id_user)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error : id_user " + id_user + " was not found");
        }else{
            return userStateService.currentState(id_user);
        }
    }

    @PostMapping("{id_user}/{state_label}")
    @ResponseStatus(HttpStatus.CREATED)
    public User_State addUserState(@PathVariable int id_user, @PathVariable String state_label) {
        if(userStateService.noUserIdFound(id_user)){
            return userStateService.saveAndFlush(id_user, state_label);
        }
        return null;
    }
}
