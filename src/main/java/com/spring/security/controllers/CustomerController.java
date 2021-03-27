package com.spring.security.controllers;

import com.spring.security.domain.entity.Customer;
import com.spring.security.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: CustomerController
 * Inside the package - com.spring.security.controllers
 * Created Date: 3/15/2021
 * Created Time: 6:30 AM
 **/
@Controller
@RequestMapping("/api/v1/security/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController (CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity getAllCustomers(){
        log.info("Inside getAllCustomers");
        List<Customer> customers = customerService.getAllCustomers();
        log.info("Customers -->" + customers);
        return new ResponseEntity(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
