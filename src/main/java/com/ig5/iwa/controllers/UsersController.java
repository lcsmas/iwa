package com.ig5.iwa.controllers;

import com.ig5.iwa.models.State;
import com.ig5.iwa.models.User;
import com.ig5.iwa.models.User_State;
import com.ig5.iwa.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/users")
@RestController
public class UsersController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping
    public List<User> list() {
        System.out.println("get all users");
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<User> get(@PathVariable Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_user "+id+" not found");
        }else {
            return userRepository.findById(id);
        }
    }

    @GetMapping
    @RequestMapping("mail/{mail}")
    public Boolean get(@PathVariable String mail) {
        if(userRepository.findByMail(mail).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the mail "+ mail +" was not found");
        }
        return userRepository.findByMail(mail).isPresent();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return userRepository.saveAndFlush(user);
    }

    @PostMapping("{id_user}/{state_label}")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUserState(@PathVariable int id_user, @PathVariable String state_label) {
        if(userRepository.findById(id_user).isPresent()){
            User u = userRepository.findById(id_user).orElse(new User());
            State state = new State(state_label);
            User_State us = new User_State(u,state);
            u.addUserState(us);
            // TODO : trouver une solution pour ajouter un state...
            // TODO : Unable to find com.ig5.iwa.models.User_State with id com.ig5.iwa.models.UserStateKey@5f901b30
            return userRepository.saveAndFlush(u);
        }
        return null;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Integer id, @RequestBody User user){
        // TODO: Ajouter ici une validation si tous les champs ont ete passes
        // sinon ,retourner une erreur 400
        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user,existingUser,"id_user");
        return userRepository.saveAndFlush(existingUser);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable int id){
        //verifier existence
        if(userRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error : id_user " + id + " was not found");
        }else{
            userRepository.deleteById(id);
        }
    }

    @PutMapping
    public User update(@PathVariable int id_user, @RequestBody User userUpdated){
        if (userRepository.findById(id_user).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error : id_user " + id_user + " was not found");
        }else{
            User userPersisted = userRepository.getOne(id_user);
            BeanUtils.copyProperties(userUpdated,userPersisted,"id_user");
            return userRepository.saveAndFlush(userPersisted);
        }
    }

}
