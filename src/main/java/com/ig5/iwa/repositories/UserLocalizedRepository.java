package com.ig5.iwa.repositories;

import com.ig5.iwa.models.UserLocalizedKey;
import com.ig5.iwa.models.UserStateKey;
import com.ig5.iwa.models.User_Localized;
import com.ig5.iwa.models.User_State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLocalizedRepository extends JpaRepository<User_Localized, UserLocalizedKey> {

    Optional<User_Localized> findTopById_IdUserOrderByDateDesc(Integer id_user);

}
