package com.spring.security.domain.repository;

import com.spring.security.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: CustomerRepository
 * Inside the package - com.spring.security.domain.repository
 * Created Date: 3/15/2021
 * Created Time: 6:09 AM
 **/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
