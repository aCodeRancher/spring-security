package com.spring.security.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: RestHeaderAuthFilter
 * Inside the package - com.spring.security.auth
 * Created Date: 3/18/2021
 * Created Time: 5:47 AM
 **/
@Slf4j
public class RestHeaderAuthFilter extends AbstractAuthenticationProcessingFilter {

    public RestHeaderAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        log.debug("Request is to process authentication");
        try{
            Authentication authResult = attemptAuthentication(httpServletRequest, httpServletResponse);
            if(authResult !=null){
                log.info("Authentication is successful");
                successfulAuthentication(httpServletRequest, httpServletResponse, chain, authResult);
            }else {
                chain.doFilter(httpServletRequest, httpServletResponse);
            }

        }catch(AuthenticationException ae){
            unsuccessfulAuthentication(httpServletRequest, httpServletResponse, ae);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        log.trace("Failed to process authentication request", failed);
        log.trace("Cleared SecurityContextHolder");
        log.trace("Handling authentication failure");
        response.sendError(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase());

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        String userName = getUserName(httpServletRequest);
        String password = getPassword(httpServletRequest);
        if(userName == null){
            userName = "";
        }
        if(password == null){
            password = "";
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);

        if(!StringUtils.isEmpty(userName)){
            return this.getAuthenticationManager().authenticate(token);
        }else{
            return null;
        }
    }

    private String getUserName(HttpServletRequest httpServletRequest){
        return httpServletRequest.getHeader("api-key");
    }

    private String getPassword(HttpServletRequest httpServletRequest){
        return httpServletRequest.getHeader("api-secret");
    }
}
