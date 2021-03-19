package com.spring.security.bootstrap;

import com.spring.security.domain.entity.Customer;
import com.spring.security.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: DataLoader
 * Inside the package - com.spring.security.bootstrap
 * Created Date: 3/15/2021
 * Created Time: 6:15 AM
 **/
@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerService customerService;

    public DataLoader(CustomerService customerService){
        this.customerService = customerService;
    }


    @Override
    public void run(String... args) throws Exception {
        addCustomer(Customer.builder()
                    .firstName("Balaji")
                    .lastName("Varadharaja")
                    .dateOfBirth(LocalDate.of(1983, 05, 21))
                    .build());
        /* BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("password"));
        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("vara"));
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        System.out.println(pbkdf2PasswordEncoder.encode("mohana")); */
    }

    private Customer addCustomer(Customer customer){
        return customerService.saveCustomer(customer);
    }
}
