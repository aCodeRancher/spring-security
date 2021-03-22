package com.spring.security.services.security;

import com.spring.security.domain.entity.security.User;
import com.spring.security.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: UserServiceImpl
 * Inside the package - com.spring.security.services
 * Created Date: 3/19/2021
 * Created Time: 6:20 AM
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUsersByUserName(String userName) {
        return userRepository.findUsersByUserName(userName);
    }

    @Override
    public Set<User> getAllUsers() {
        return userRepository.findAll().stream().collect(Collectors.toSet());
    }
}
