package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.TestCleanup;
import com.cetekot.service.authentication.persistence.entity.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application.properties" )
public class TokenServiceIntegrationTest {

    @Autowired
    private TokenService service;

    @Autowired
    private TestCleanup testCleanup;

    @BeforeEach
    public void setUp() {

        testCleanup.performCleanup();
    }

    @Test
    public void testCrud() {

        Assertions.assertEquals( 0, service.list().size() );
        Token token = new Token( "Test.Token.1", LocalDateTime.now() );
        service.save( token );
        Assertions.assertEquals( 1, service.list().size() );

        Token dbToken = service.findById( token.getToken() );
        Assertions.assertEquals( token.getValidTo().truncatedTo( ChronoUnit.SECONDS ), dbToken.getValidTo().truncatedTo( ChronoUnit.SECONDS ) );

        Token anotherToken = new Token( "Test.Token.2", LocalDateTime.now() );
        service.save( anotherToken );
        Assertions.assertEquals( 2, service.list().size() );

        dbToken = service.findById( anotherToken.getToken() );
        Assertions.assertEquals( anotherToken.getValidTo().truncatedTo( ChronoUnit.SECONDS ), dbToken.getValidTo().truncatedTo( ChronoUnit.SECONDS ) );

        service.delete( token );
        Assertions.assertEquals( 1, service.list().size() );

        service.delete( anotherToken );
        Assertions.assertEquals( 0, service.list().size() );
    }
}