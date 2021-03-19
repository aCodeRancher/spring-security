package com.spring.security.encoders;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: PasswordEncodingTest
 * Inside the package - com.spring.security.encoders
 * Created Date: 3/17/2021
 * Created Time: 6:18 AM
 **/
public class PasswordEncodingTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    static final String PASSWORD = "password";

    @Test
    void hashingMD5Example(){
        logger.info(DigestUtils.md5DigestAsHex(PASSWORD.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void hashingBCryptExample(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        logger.info(bCryptPasswordEncoder.encode(PASSWORD));
    }

    @Test
    void hashingSCryptExample(){
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        logger.info(pbkdf2PasswordEncoder.encode(PASSWORD));
    }
}
