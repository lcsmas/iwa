package com.ig5.iwa.services;

import com.ig5.iwa.models.User;
import com.ig5.iwa.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Boolean noUserIdFound(int id){
        return userRepository.findById(id).isEmpty();
    }

    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    public Boolean existsMail(String mail){
        return userRepository.findByMail(mail).isEmpty();
    }

    public Integer findMail(String mail){
        return userRepository.findByMail(mail).map(User::getId_user).orElse(null);
    }

    public User create(@RequestBody final User user) {
        return userRepository.saveAndFlush(user);
    }

    public User update( Integer id,  User user){
        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user,existingUser,"id_user");
        return userRepository.saveAndFlush(existingUser);
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }

    public User saveAndFlush(User user){
        return userRepository.saveAndFlush(user);
    }

}
