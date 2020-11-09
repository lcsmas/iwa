package com.ig5.iwa.controllers;

import com.ig5.iwa.models.State;
import com.ig5.iwa.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/states")
@RestController
public class StatesController {
    @Autowired
    private StateRepository stateRepository;

    @GetMapping
    public List<State> list() {  return stateRepository.findAll(); }

    @GetMapping
    @RequestMapping ("{id}")
    public Optional<State> get(@PathVariable Integer id) {
        return stateRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State create(@RequestBody final State state) {
        return stateRepository.saveAndFlush(state);
    }
}