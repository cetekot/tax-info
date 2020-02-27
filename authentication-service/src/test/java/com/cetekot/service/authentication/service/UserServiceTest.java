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

    @Test( expected = UserNotFoundException.class )
    public void testFindByWrongUserName() {

        service.findByUsername( "some.wrong.username" );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }

    @Test( expected = UserNotFoundException.class )
    public void testFindByWrongUserNameAndPassword() {

        service.findByUsernameAndPassword( "some.wrong.username", "some.wrong.password" );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }

    @Test( expected = UserNotFoundException.class )
    public void testFindByNullUserName() {

        service.findByUsername( null );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }

    @Test( expected = UserNotFoundException.class )
    public void testFindByNullUserNameAndPassword() {

        service.findByUsernameAndPassword( null, null );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }

    @Test( expected = UserNotFoundException.class )
    public void testFindByEmptyUserName() {

        service.findByUsername( "" );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }

    @Test( expected = UserNotFoundException.class )
    public void testFindByEmptyUserNameAndPassord() {

        service.findByUsernameAndPassword( "", "" );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }
}
