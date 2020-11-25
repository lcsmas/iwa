package com.ig5.iwa.controllers;

import com.ig5.iwa.models.State;
import com.ig5.iwa.repositories.StateRepository;
import com.ig5.iwa.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/states")
@RestController
public class StatesController {
    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> list() {  return stateService.findAll();}

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<State> get(@PathVariable Integer id) {
        if(stateService.noStateIdFound(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id_state "+id+" not found");
        }else{
            return stateService.findStateById(id);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State create(@RequestBody final State state) {
        return stateService.create(state);
    }
}