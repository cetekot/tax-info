package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.TokenNotFoundException;
import com.cetekot.service.authentication.persistence.entity.Token;
import com.cetekot.service.authentication.persistence.repository.TokenRepository;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

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

    @Test
    public void testWrongTokenData() {

        try {

            service.findById( "some.wrong.token.id" );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( TokenNotFoundException expected ) {
        }
    }

    @Test
    public void testMissingTokenData() {

        try {

            service.findById( null );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( TokenNotFoundException expected ) {
        }
    }

    @Test
    public void testEmptyTokenData() {

        try {

            service.findById( "" );
            Assert.fail( "Should have got TokenNotFoundException here." );
        }
        catch( TokenNotFoundException expected ) {
        }
    }

    @Test
    public void testTokenValidity() {

        Calendar c = Calendar.getInstance();
        c.add( Calendar.YEAR, -1 );
        Token token = new Token( "Test.Token", c.getTime() );
        Assert.assertFalse( service.isValid( token ) );

        c.add( Calendar.YEAR, 3 );
        token = new Token( "Test.Token", c.getTime() );
        Assert.assertTrue( service.isValid( token ) );
    }
}
