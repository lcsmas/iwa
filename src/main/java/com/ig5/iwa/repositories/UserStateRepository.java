package com.ig5.iwa.repositories;

import com.ig5.iwa.models.UserStateKey;
import com.ig5.iwa.models.User_State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStateRepository extends JpaRepository<User_State, UserStateKey> {

}
