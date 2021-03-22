package com.spring.security.domain.entity.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: User
 * Inside the package - com.spring.security.domain.entity.security
 * Created Date: 3/19/2021
 * Created Time: 5:57 AM
 **/
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SECURIY_USER")
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    private String userName;

    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID")})
    private Set<Authority> authorities;

    @Builder.Default
    private boolean accountNotExpired = true;

    @Builder.Default
    private boolean accountNotLocked = true;

    @Builder.Default
    private boolean credentialsNotExpired = true;

    @Builder.Default
    private boolean enabled = true;
}
