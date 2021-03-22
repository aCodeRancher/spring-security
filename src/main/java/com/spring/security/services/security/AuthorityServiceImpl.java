package com.spring.security.services.security;

import com.spring.security.domain.entity.security.Authority;
import com.spring.security.domain.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: AuthorityServiceImpl
 * Inside the package - com.spring.security.services.security
 * Created Date: 3/19/2021
 * Created Time: 6:35 AM
 **/
@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Set<Authority> getAllAuthorities() {
        return authorityRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Optional<Authority> getAuthorityById(Long authorityId) {
        return authorityRepository.findById(authorityId);
    }
}
