package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.ConfigNotFoundException;
import com.cetekot.service.authentication.persistence.repository.ConfigRepository;
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
public class ConfigServiceTest {

    @Mock
    private ConfigRepository repository;

    private ConfigService service;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks( this );
        service = new ConfigService( repository );
    }

    @Test( expected = ConfigNotFoundException.class )
    public void testWrongConfigData() {

        service.findById( "some.wrong.key" );
        Assert.fail( "Should have got ConfigNotFoundException here." );
    }

    @Test( expected = ConfigNotFoundException.class )
    public void testMissingConfigData() {

        service.findById( null );
        Assert.fail( "Should have got ConfigNotFoundException here." );
    }

    @Test( expected = ConfigNotFoundException.class )
    public void testEmptyConfigData() {

        service.findById( "" );
        Assert.fail( "Should have got ConfigNotFoundException here." );
    }
}
