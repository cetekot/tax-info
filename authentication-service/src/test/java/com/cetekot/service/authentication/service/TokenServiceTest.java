package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.TokenNotFoundException;
import com.cetekot.service.authentication.persistence.entity.Token;
import com.cetekot.service.authentication.persistence.repository.TokenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

/**
 * Copyright:    Copyright (c) 2020
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class TokenServiceTest {

    @Mock
    private TokenRepository repository;

    private TokenService service;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks( this );
        service = new TokenService( repository );
    }

    @Test
    public void testWrongTokenData() {

        Assertions.assertThrows( TokenNotFoundException.class, () -> service.findById( "some.wrong.token.id" ), "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testMissingTokenData() {

        Assertions.assertThrows( TokenNotFoundException.class, () -> service.findById( null ), "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testEmptyTokenData() {

        Assertions.assertThrows( TokenNotFoundException.class, () -> service.findById( "" ), "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testTokenValidity() {

        Token token = new Token( "Test.Token", LocalDateTime.now().minusYears( 1 ) );
        Assertions.assertFalse( service.isValid( token ) );

        token = new Token( "Test.Token", LocalDateTime.now().plusYears( 3 ) );
        Assertions.assertTrue( service.isValid( token ) );
    }
}
