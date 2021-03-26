package com.spring.security.bootstrap;

import com.spring.security.domain.entity.Customer;
import com.spring.security.domain.entity.security.Authority;
import com.spring.security.domain.entity.security.User;
import com.spring.security.services.CustomerService;
import com.spring.security.services.security.AuthorityService;
import com.spring.security.services.security.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    private final UserService userService;

    private final AuthorityService authorityService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DataLoader(CustomerService customerService,
                      UserService userService,
                      AuthorityService authorityService,
                      BCryptPasswordEncoder bCryptPasswordEncoder ){
        this.customerService = customerService;
        this.authorityService = authorityService;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inside the Data Loader");
        addCustomer(Customer.builder()
                    .firstName("Balaji")
                    .lastName("Varadharaja")
                    .dateOfBirth(LocalDate.of(1983, 05, 21))
                    .build());
        createAuthorities();
        //System.out.println("Admin Authority:"+authorities.stream().filter("ADMIN"::equals).findAny().orElse(null));
        //createUsers(authorities);
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


    private Set<Authority> createAuthorities(){
        Set<Authority> authorities = new HashSet<>();
        Authority authority1 = authorityService.save(Authority.builder()
                .role("ROLE_ADMIN")
                .build());
        userService.save(User.builder()
                .userName("balaji")
                .password("{bcrypt}"+bCryptPasswordEncoder.encode("vara"))
                .authority(authority1)
                .build());
        Authority authority2 = authorityService.save(Authority.builder()
                .role("ROLE_USER")
                .build());
        User user2 = userService.save(User.builder()
                .userName("pooja")
                .password("{bcrypt}"+bCryptPasswordEncoder.encode("mohana"))
                .authority(authority2)
                .build());
        Authority authority3= authorityService.save(Authority.builder()
                .role("ROLE_CUSTOMER")
                .build());
        userService.save(User.builder()
                .userName("user")
                .password("{bcrypt}"+bCryptPasswordEncoder.encode("password"))
                .authority(authority3)
                .build());
        authorities.add(authority1);
        authorities.add(authority2);
        authorities.add(authority3);
        return authorities;
    }

}
