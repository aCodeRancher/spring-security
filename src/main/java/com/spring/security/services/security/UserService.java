package com.spring.security.services.security;

import com.spring.security.domain.entity.security.User;

import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: UserService
 * Inside the package - com.spring.security.services
 * Created Date: 3/19/2021
 * Created Time: 6:18 AM
 **/
public interface UserService {

    User save(User user);
    Optional<User> getUserById(Long userId);
    Optional<User> getUsersByUserName(String userName);
    Set<User> getAllUsers();
}
