package com.spring.security.domain.entity.security;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: Authority
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
public class Authority {

    @Id
    @GeneratedValue
    private Long authorityId;

    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return role.equals(authority.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}
