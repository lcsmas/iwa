package com.ig5.iwa.repositories;

import com.ig5.iwa.models.UserStateKey;
import com.ig5.iwa.models.User_State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserStateRepository extends JpaRepository<User_State, UserStateKey> {

    Optional<User_State> findTopById_IdUserOrderByDateDesc(Integer id_user);

    List<User_State> findAllById_IdUserOrderByDateDesc(Integer id_user);

}
