package com.ig5.iwa.controllers;

import com.ig5.iwa.models.User;
import com.ig5.iwa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}
