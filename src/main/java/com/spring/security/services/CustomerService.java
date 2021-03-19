package com.spring.security.services;

import com.spring.security.domain.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: CustomerService
 * Inside the package - com.spring.security
 * Created Date: 3/15/2021
 * Created Time: 6:10 AM
 **/
public interface CustomerService {

    Optional<Customer> getCustomerById(Long customerId);

    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();
}
