package com.spring.security.auth;

import com.spring.security.domain.entity.security.Authority;
import com.spring.security.domain.entity.security.User;
import com.spring.security.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: JpaUserDetailsService
 * Inside the package - com.spring.security.auth
 * Created Date: 3/22/2021
 * Created Time: 6:21 AM
 **/
@Slf4j
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Getting User Information via JPA");

        User user = userRepository.findUsersByUserName(username).orElseThrow(() -> {
            return new UsernameNotFoundException("User name:" + username + "not found");
        });

        org.springframework.security.core.userdetails.User springUser =
                new org.springframework.security.core.userdetails.User(
                        user.getUserName(),
                        user.getPassword(),
                        user.isEnabled(),
                        user.isAccountNotExpired(),
                        user.isCredentialsNotExpired(),
                        user.isAccountNotLocked(),
                        convertToSpringAuthorities(user.getAuthorities())
                );
        return springUser;
    }

    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<Authority> authorities) {
        if(authorities!=null &&
                authorities.size() >0){
            return authorities.stream()
                    .map(Authority :: getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }else{
            return new HashSet<>();
        }
    }
}
