package com.ig5.iwa.controllers;

import com.ig5.iwa.models.*;
import com.ig5.iwa.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
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

    //Todo : Faire des tests
    @GetMapping
    @RequestMapping("mail/{mail}")
    public Integer getIdByMail(@PathVariable String mail) {
        if(userRepository.findByMail(mail).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the mail "+ mail +" was not found");
        }
        return userRepository.findByMail(mail).map(User::getId_user).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return userRepository.saveAndFlush(user);
    }


    @PostMapping(value = "addLocation/{id_user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User addUserLocalized(@PathVariable int id_user, @RequestBody Location data) {
        if(userRepository.findById(id_user).isPresent()){
            User u = userRepository.findById(id_user).orElse(new User());
            Location location = new Location(data.getLongitude(),data.getLatitude());
            User_Localized ul = new User_Localized(u,location);
            u.addUserLocation(ul);
            return userRepository.saveAndFlush(u);
        }
        return null;
    }

    @PostMapping("{id_user}/{state_label}")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUserState(@PathVariable int id_user, @PathVariable String state_label) {
        if(userRepository.findById(id_user).isPresent()){
            User u = userRepository.findById(id_user).orElse(new User());
            State state = new State(state_label);
            User_State us = new User_State(u,state);
            u.addUserState(us);
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
