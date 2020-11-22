package com.ig5.iwa.controllers;

import com.ig5.iwa.models.*;
import com.ig5.iwa.repositories.UserStateRepository;
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
    public UserStateRepository userStateRepository;


    @GetMapping
    public List<User_State> list() {
        System.out.println("get all users");
        return userStateRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id_user}/currentState")
    public Optional<String> get(@PathVariable int id_user) {
        if(userStateRepository.findTopById_IdUserOrderByDateDesc(id_user).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error : id_user " + id_user + " was not found");
        }else{
            Optional<String> currentState = userStateRepository.findTopById_IdUserOrderByDateDesc(id_user).map(User_State::getState).map(State::getLabel_state);
            return currentState;
        }
    }
}
