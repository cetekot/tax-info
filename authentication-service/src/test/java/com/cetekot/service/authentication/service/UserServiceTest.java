package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.UserNotFoundException;
import com.cetekot.service.authentication.persistence.repository.UserRepository;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    private UserService service;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks( this );
        service = new UserService( repository );
    }

    @Test
    public void testFindByWrongUserData() {

        try {

            service.findByUsername( "some.wrong.username" );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( UserNotFoundException expected ) {
        }

        try {

            service.findByUsernameAndPassword( "some.wrong.username", "some.wrong.password" );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( UserNotFoundException expected ) {
        }
    }

    @Test
    public void testFindByNullUserData() {

        try {

            service.findByUsername( null );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( UserNotFoundException expected ) {
        }

        try {

            service.findByUsernameAndPassword( null, null );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( UserNotFoundException expected ) {
        }
    }

    @Test
    public void testFindByEmptyUserData() {

        try {

            service.findByUsername( "" );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( UserNotFoundException expected ) {
        }

        try {

            service.findByUsernameAndPassword( "", "" );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( UserNotFoundException expected ) {
        }
    }
}
