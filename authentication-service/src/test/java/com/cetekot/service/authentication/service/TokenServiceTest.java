package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.TokenNotFoundException;
import com.cetekot.service.authentication.persistence.entity.Token;
import com.cetekot.service.authentication.persistence.repository.TokenRepository;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class TokenServiceTest {

    @Mock
    private TokenRepository repository;

    private TokenService service;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks( this );
        service = new TokenService( repository );
    }

    @Test( expected = TokenNotFoundException.class )
    public void testWrongTokenData() {

        service.findById( "some.wrong.token.id" );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }

    @Test( expected = TokenNotFoundException.class )
    public void testMissingTokenData() {

        service.findById( null );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }

    @Test( expected = TokenNotFoundException.class )
    public void testEmptyTokenData() {

        service.findById( "" );
        Assert.fail( "Should have got TokenNotFoundException here." );
    }

    @Test
    public void testTokenValidity() {

        Token token = new Token( "Test.Token", LocalDateTime.now().minusYears( 1 ) );
        Assert.assertFalse( service.isValid( token ) );

        token = new Token( "Test.Token", LocalDateTime.now().plusYears( 3 ) );
        Assert.assertTrue( service.isValid( token ) );
    }
}
