package com.ucsal.physicalSpaceManagement.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucsal.physicalSpaceManagement.user.Enums.UserType;
import com.ucsal.physicalSpaceManagement.user.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    public List<User> findByUserType(UserType userType);

}
