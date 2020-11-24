package com.ig5.iwa.controllers;

import com.ig5.iwa.models.*;
import com.ig5.iwa.repositories.StateRepository;
import com.ig5.iwa.repositories.UserRepository;
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

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public StateRepository stateRepository;

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

    @PostMapping("{id_user}/{state_label}")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUserState(@PathVariable int id_user, @PathVariable String state_label) {
        if(userRepository.findById(id_user).isPresent()){
            User u = userRepository.findById(id_user).orElse(new User());
            State state = new State(state_label);
            State stateSave = stateRepository.save(state);
            User_State us = new User_State(u,stateSave);
            u.addUserState(us);
            return userRepository.saveAndFlush(u);
        }
        return null;
    }
}
