package com.spring.security.services.security;

import com.spring.security.domain.entity.security.Authority;

import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: AuthorityService
 * Inside the package - com.spring.security.services.security
 * Created Date: 3/19/2021
 * Created Time: 6:34 AM
 **/
public interface AuthorityService {

    Set<Authority> getAllAuthorities();

    Authority save(Authority authority);

    Optional<Authority> getAuthorityById(Long authorityId);
}
