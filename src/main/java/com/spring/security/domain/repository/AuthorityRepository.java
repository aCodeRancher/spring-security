package com.spring.security.domain.repository;

import com.spring.security.domain.entity.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: AuthorityRepository
 * Inside the package - com.spring.security.domain.repository
 * Created Date: 3/19/2021
 * Created Time: 6:17 AM
 **/
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
