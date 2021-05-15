package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.TestCleanup;
import com.cetekot.service.authentication.persistence.entity.Token;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application.properties" )
public class TokenServiceIntegrationTest {

    @Autowired
    private TokenService service;

    @Autowired
    private TestCleanup testCleanup;

    @Before
    public void setUp() {

        testCleanup.performCleanup();
    }

    @Test
    public void testCrud() {

        Assert.assertEquals( 0, service.list().size() );
        Token token = new Token( "Test.Token.1", LocalDateTime.now() );
        service.save( token );
        Assert.assertEquals( 1, service.list().size() );

        Token dbToken = service.findById( token.getToken() );
        Assert.assertEquals( token.getValidTo(), dbToken.getValidTo() );

        Token anotherToken = new Token( "Test.Token.2", LocalDateTime.now() );
        service.save( anotherToken );
        Assert.assertEquals( 2, service.list().size() );

        dbToken = service.findById( anotherToken.getToken() );
        Assert.assertEquals( anotherToken.getValidTo(), dbToken.getValidTo() );

        service.delete( token );
        Assert.assertEquals( 1, service.list().size() );

        service.delete( anotherToken );
        Assert.assertEquals( 0, service.list().size() );
    }
}