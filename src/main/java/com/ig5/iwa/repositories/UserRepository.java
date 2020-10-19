package com.ig5.iwa.repositories;

import com.ig5.iwa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
