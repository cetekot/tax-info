package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.UserNotFoundException;
import com.cetekot.service.authentication.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Copyright:    Copyright (c) 2020-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    private UserService service;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks( this );
        service = new UserService( repository );
    }

    @Test
    public void testFindByWrongUserName() {

        Assertions.assertThrows( UserNotFoundException.class, () -> service.findByUsername( "some.wrong.username" ), "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testFindByWrongUserNameAndPassword() {

        Assertions.assertThrows( UserNotFoundException.class, () -> service.findByUsernameAndPassword( "some.wrong.username", "some.wrong.password" ), "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testFindByNullUserName() {

        Assertions.assertThrows( UserNotFoundException.class, () -> service.findByUsername( null ), "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testFindByNullUserNameAndPassword() {

        Assertions.assertThrows( UserNotFoundException.class, () -> service.findByUsernameAndPassword( null, null ), "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testFindByEmptyUserName() {

        Assertions.assertThrows( UserNotFoundException.class, () -> service.findByUsername( "" ), "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testFindByEmptyUserNameAndPassord() {

        Assertions.assertThrows( UserNotFoundException.class, () -> service.findByUsernameAndPassword( "", "" ), "Should have got TokenNotFoundException here." );
    }
}
