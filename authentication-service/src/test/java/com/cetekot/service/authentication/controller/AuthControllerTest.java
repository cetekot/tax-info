package com.cetekot.service.authentication.controller;

import com.cetekot.service.authentication.TestCleanup;
import com.cetekot.service.authentication.dto.LoginDto;
import com.cetekot.service.authentication.persistence.entity.Config;
import com.cetekot.service.authentication.persistence.entity.User;
import com.cetekot.service.authentication.service.ConfigService;
import com.cetekot.service.authentication.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application.properties" )
public class AuthControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ServerProperties serverProperties;

    @LocalServerPort
    private int port;

    @Autowired
    private ConfigService configService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestCleanup testCleanup;

    @BeforeEach
    public void setUp() {

        testCleanup.performCleanup();
    }

    private String getUrl() {

        return "http://" + serverProperties.getAddress().getHostAddress() + ":" + port + "/api/v1/auth";
    }

    @Test
    public void testAuthenticate() {

        configService.save( new Config( Config.TOKEN_INTERVAL, "1000000" ) );

        User testUser = new User( "Test.User", "Test.Password" );
        userService.save( testUser );

        // No user/password
        LoginDto loginDto = new LoginDto( null, null );
        ResponseEntity<String> response = testRestTemplate.exchange( getUrl(), HttpMethod.POST, new HttpEntity<>( loginDto ), String.class );
        Assertions.assertEquals( 404, response.getStatusCodeValue() );

        // Existing user/no password
        loginDto = new LoginDto( testUser.getUsername(), null );
        response = testRestTemplate.exchange( getUrl(), HttpMethod.POST, new HttpEntity<>( loginDto ), String.class );
        Assertions.assertEquals( 404, response.getStatusCodeValue() );

        // No user/existing password
        loginDto = new LoginDto( null, testUser.getPassword() );
        response = testRestTemplate.exchange( getUrl(), HttpMethod.POST, new HttpEntity<>( loginDto ), String.class );
        Assertions.assertEquals( 404, response.getStatusCodeValue() );

        // Existing user/existing password
        loginDto = new LoginDto( testUser.getUsername(), testUser.getPassword() );
        response = testRestTemplate.exchange( getUrl(), HttpMethod.POST, new HttpEntity<>( loginDto ), String.class );
        Assertions.assertEquals( 200, response.getStatusCodeValue() );
    }

    @Test
    public void testCheckToken() {

        configService.save( new Config( Config.TOKEN_INTERVAL, "1000000" ) );

        User testUser = new User( "Test.User", "Test.Password" );
        userService.save( testUser );

        LoginDto loginDto = new LoginDto( testUser.getUsername(), testUser.getPassword() );
        ResponseEntity<String> response = testRestTemplate.exchange( getUrl(), HttpMethod.POST, new HttpEntity<>( loginDto ), String.class );
        Assertions.assertEquals( 200, response.getStatusCodeValue() );
        String properToken = response.getBody();

        // Wrong token
        response = testRestTemplate.exchange( getUrl() + "/d", HttpMethod.GET, new HttpEntity<>( null ), String.class );
        Assertions.assertEquals( 401, response.getStatusCodeValue() );

        // Proper token
        response = testRestTemplate.exchange( getUrl() + "/" + properToken, HttpMethod.GET, new HttpEntity<>( null ), String.class );
        Assertions.assertEquals( 200, response.getStatusCodeValue() );
    }
}