package com.spring.security.domain.repository;

import com.spring.security.domain.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: UserRepository
 * Inside the package - com.spring.security.domain.repository
 * Created Date: 3/19/2021
 * Created Time: 6:15 AM
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUsersByUserName(String userName);
}
