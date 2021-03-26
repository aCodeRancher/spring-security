package com.spring.security.config;

import com.spring.security.auth.RestHeaderAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: SecurityConfig
 * Inside the package - com.spring.security.config
 * Created Date: 3/15/2021
 * Created Time: 6:52 AM
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /* In memory authentication
    *
    *
     */

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                // Raw Password: password
                .password("{bcrypt}$2a$10$NZikd9JW.8.ti.gvON4cfOaRm5ivnmy3fQXBkPXzC7fStyB78Yxje")
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser("balaji")
                // Raw Password: vara
                .password("{bcrypt}$2a$10$OAARRLgZnWrXT.M3F7m/q.YjCxXW3W/yCxpo9plkH./hquEORDOgm")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("pooja")
                // Raw Password: mohana
                .password("{pbkdf2}ffecb04c756bb26d0ceb29f9b0bca63a972d9fdfa11608fc82eb1e73dbdaab0d91a3be091112efd4")
                .roles("USER");
    }*/


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //httpSecurity.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
          //      UsernamePasswordAuthenticationFilter.class).csrf().disable();
        httpSecurity.authorizeRequests(
                authorize -> {
                    authorize.
                            antMatchers("/h2-console/**").permitAll().
                            mvcMatchers(HttpMethod.POST,
                                    "/api/v1/security/customer/**").hasRole("ADMIN")
                            .mvcMatchers(HttpMethod.GET,
                                    "/api/v1/security/customer").permitAll();
                }
        ).authorizeRequests().anyRequest().authenticated()
                .and().csrf().disable();
        httpSecurity.headers().frameOptions().sameOrigin();

    }

    /*
    * Needed if custom filter needs to be implemented
    *
    *
    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager){
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(
                new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    } */

}
