package com.ig5.iwa.controllers;

import com.ig5.iwa.models.*;
import com.ig5.iwa.services.UserService;
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
    public UserService userService;

    @GetMapping
    public List<User> list() {
        return userService.findAll();
    }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<User> get(@PathVariable Integer id) {
        if(userService.noUserIdFound(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_user "+id+" not found");
        }else {
            return userService.findUserById(id);
        }
    }

    @GetMapping
    @RequestMapping("mail/{mail}")
    public Integer getIdByMail(@PathVariable String mail) {
        if(userService.existsMail(mail)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the mail "+ mail +" was not found");
        }
        return userService.findMail(mail);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return userService.create(user);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Integer id, @RequestBody User user){
        if(userService.noUserIdFound(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_user "+id+" not found");
        }else {
            return userService.update(id,user);
        }
    }

    @DeleteMapping
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable int id){
        if(userService.noUserIdFound(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error : id_user " + id + " was not found");
        }else{
            userService.delete(id);
        }
    }


    @PutMapping
    public User update(@PathVariable int id_user, @RequestBody User userUpdated){
        if (userService.noUserIdFound(id_user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Error : id_user " + id_user + " was not found");
        }else{
            return userService.update(id_user,userUpdated);
        }
    }
}
