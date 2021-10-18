package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.TestCleanup;
import com.cetekot.service.authentication.persistence.entity.Config;
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
public class ConfigServiceIntegrationTest {

    @Autowired
    private ConfigService service;

    @Autowired
    private TestCleanup testCleanup;

    @BeforeEach
    public void setUp() {

        testCleanup.performCleanup();
    }

    @Test
    public void testCrud() {

        Assertions.assertEquals( 0, service.list().size() );
        Config config = new Config( "Some.Test.Config", "TestValue1234" );
        service.save( config );
        Assertions.assertEquals( 1, service.list().size() );

        Config dbConfig = service.findById( config.getKey() );
        Assertions.assertEquals( config.getValue(), dbConfig.getValue() );

        Config anotherConfig = new Config( "Another.Test.Config", "TestValue0987" );
        service.save( anotherConfig );
        Assertions.assertEquals( 2, service.list().size() );

        dbConfig = service.findById( anotherConfig.getKey() );
        Assertions.assertEquals( anotherConfig.getValue(), dbConfig.getValue() );

        service.delete( config );
        Assertions.assertEquals( 1, service.list().size() );

        service.delete( anotherConfig );
        Assertions.assertEquals( 0, service.list().size() );
    }
}