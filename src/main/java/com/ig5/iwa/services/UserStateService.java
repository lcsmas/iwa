package com.ig5.iwa.services;


import com.ig5.iwa.models.*;
import com.ig5.iwa.repositories.UserLocalizedRepository;
import com.ig5.iwa.repositories.UserStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStateService {

    @Autowired
    public UserStateRepository userStateRepository;

    @Autowired
    public UserService userService;

    @Autowired
    public StateService stateService;


    public List<User_State> findAll() {
        return userStateRepository.findAll();
    }

    public List<User_State> listById(int id_user){
        return userStateRepository.findAllById_IdUserOrderByDateDesc(id_user);
    }

    public Optional<String> currentState(int id_user){
        return userStateRepository.findTopById_IdUserOrderByDateDesc(id_user).map(User_State::getState).map(State::getLabel_state);
    }

    public Boolean findOneUserStateWithUserId(int id_user){
        return userStateRepository.findTopById_IdUserOrderByDateDesc(id_user).isEmpty();
    }

    public Boolean noUserIdFound(int id){
        return userService.noUserIdFound(id);
    }

        public User saveAndFlush(int id_user, String state_label) {
        /*
        User u = userService.findUserById(id_user).orElse(new User());
        State state = new State(state_label);
        State stateSave = stateService.save(state);
        User_State us = new User_State(u,stateSave);
        u.addUserState(us);
        return userService.saveAndFlush(u);

         */
        User u = userService.findUserById(id_user).orElse(new User());
        State state = new State(state_label);
        State stateSave = stateService.save(state);
        User_State us = new User_State(u,stateSave);
        u.addUserState(us);
        System.out.println("------------------+" + us.getState());
        System.out.println("------------------+" + us.getUser());
        u.addUserState(us);
        return userService.saveAndFlush(u);
    }
}