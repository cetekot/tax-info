package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.TestCleanup;
import com.cetekot.service.authentication.persistence.entity.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class UserServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Autowired
    private TestCleanup testCleanup;

    @Before
    public void setUp() {

        testCleanup.performCleanup();
    }

    @Test
    public void testCrud() {

        Assert.assertEquals( 0, service.list().size() );
        User user = new User( "Some.Great.User", "TestPass1234" );
        service.save( user );
        Assert.assertEquals( 1, service.list().size() );

        User dbUser = service.findByUsername( user.getUsername() );
        Assert.assertEquals( user.getPassword(), dbUser.getPassword() );
        User dbUserPass = service.findByUsernameAndPassword( user.getUsername(), user.getPassword() );
        Assert.assertEquals( dbUser, dbUserPass );

        User anotherUser = new User( "Another.Great.User", "TestPass0987" );
        service.save( anotherUser );
        Assert.assertEquals( 2, service.list().size() );

        dbUser = service.findByUsername( anotherUser.getUsername() );
        Assert.assertEquals( anotherUser.getPassword(), dbUser.getPassword() );
        dbUserPass = service.findByUsernameAndPassword( anotherUser.getUsername(), anotherUser.getPassword() );
        Assert.assertEquals( dbUser, dbUserPass );

        service.delete( user );
        Assert.assertEquals( 1, service.list().size() );

        service.delete( anotherUser );
        Assert.assertEquals( 0, service.list().size() );
    }
}