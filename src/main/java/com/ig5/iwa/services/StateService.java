package com.ig5.iwa.services;


import com.ig5.iwa.models.State;
import com.ig5.iwa.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    public StateRepository stateRepository;

    public Boolean noStateIdFound(int id){
        return stateRepository.findById(id).isEmpty();
    }

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public Optional<State> findStateById(Integer id) {
        return stateRepository.findById(id);
    }

    public State getStateById(Integer id) {
        return stateRepository.getOne(id);
    }

    public State create(State state) {
        return stateRepository.saveAndFlush(state);
    }

    public State save(State state) {
        return stateRepository.save(state);
    }

}
