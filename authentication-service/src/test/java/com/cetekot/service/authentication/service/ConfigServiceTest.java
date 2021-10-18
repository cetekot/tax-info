package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.ConfigNotFoundException;
import com.cetekot.service.authentication.persistence.repository.ConfigRepository;
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
public class ConfigServiceTest {

    @Mock
    private ConfigRepository repository;

    private ConfigService service;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks( this );
        service = new ConfigService( repository );
    }

    @Test
    public void testWrongConfigData() {

        Assertions.assertThrows( ConfigNotFoundException.class, () -> service.findById( "some.wrong.key" ), "Should have got ConfigNotFoundException here." );
    }

    @Test
    public void testMissingConfigData() {

        Assertions.assertThrows( ConfigNotFoundException.class, () -> service.findById( null ), "Should have got ConfigNotFoundException here." );
    }

    @Test
    public void testEmptyConfigData() {

        Assertions.assertThrows( ConfigNotFoundException.class, () -> service.findById( "" ), "Should have got ConfigNotFoundException here." );
    }
}
