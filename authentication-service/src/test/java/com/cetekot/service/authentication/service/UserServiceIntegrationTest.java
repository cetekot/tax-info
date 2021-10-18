package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.TestCleanup;
import com.cetekot.service.authentication.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class UserServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Autowired
    private TestCleanup testCleanup;

    @BeforeEach
    public void setUp() {

        testCleanup.performCleanup();
    }

    @Test
    public void testCrud() {

        Assertions.assertEquals( 0, service.list().size() );
        User user = new User( "Some.Great.User", "TestPass1234" );
        service.save( user );
        Assertions.assertEquals( 1, service.list().size() );

        User dbUser = service.findByUsername( user.getUsername() );
        Assertions.assertEquals( user.getPassword(), dbUser.getPassword() );
        User dbUserPass = service.findByUsernameAndPassword( user.getUsername(), user.getPassword() );
        Assertions.assertEquals( dbUser, dbUserPass );

        User anotherUser = new User( "Another.Great.User", "TestPass0987" );
        service.save( anotherUser );
        Assertions.assertEquals( 2, service.list().size() );

        dbUser = service.findByUsername( anotherUser.getUsername() );
        Assertions.assertEquals( anotherUser.getPassword(), dbUser.getPassword() );
        dbUserPass = service.findByUsernameAndPassword( anotherUser.getUsername(), anotherUser.getPassword() );
        Assertions.assertEquals( dbUser, dbUserPass );

        service.delete( user );
        Assertions.assertEquals( 1, service.list().size() );

        service.delete( anotherUser );
        Assertions.assertEquals( 0, service.list().size() );
    }
}