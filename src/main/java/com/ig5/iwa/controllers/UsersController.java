package com.ig5.iwa.controllers;

import com.ig5.iwa.models.State;
import com.ig5.iwa.models.User;
import com.ig5.iwa.models.UserStateKey;
import com.ig5.iwa.models.User_State;
import com.ig5.iwa.repositories.UserRepository;
import com.ig5.iwa.repositories.UserStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("api/v1/users")
@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> list() {
        System.out.println("get all users");
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<User> get(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return userRepository.saveAndFlush(user);
    }

    @PostMapping("{id_user}/{state_label}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUserState(@RequestBody int id_user, String state_label) {
        if(userRepository.findById(id_user).isPresent()){
            User u = userRepository.findById(id_user).orElse(new User());
            u.setStates(u.getStates().stream()
                    .map(user_state -> {
                        User_State us = new User_State(u,new State(state_label));
                        return us;
                    }).collect(Collectors.toSet())
            );
        }
    }
}
