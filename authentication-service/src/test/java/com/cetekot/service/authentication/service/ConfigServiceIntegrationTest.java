package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.TestCleanup;
import com.cetekot.service.authentication.persistence.entity.Config;
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
public class ConfigServiceIntegrationTest {

    @Autowired
    private ConfigService service;

    @Autowired
    private TestCleanup testCleanup;

    @Before
    public void setUp() {

        testCleanup.performCleanup();
    }

    @Test
    public void testCrud() {

        Assert.assertEquals( 0, service.list().size() );
        Config config = new Config( "Some.Test.Config", "TestValue1234" );
        service.save( config );
        Assert.assertEquals( 1, service.list().size() );

        Config dbConfig = service.findById( config.getKey() );
        Assert.assertEquals( config.getValue(), dbConfig.getValue() );

        Config anotherConfig = new Config( "Another.Test.Config", "TestValue0987" );
        service.save( anotherConfig );
        Assert.assertEquals( 2, service.list().size() );

        dbConfig = service.findById( anotherConfig.getKey() );
        Assert.assertEquals( anotherConfig.getValue(), dbConfig.getValue() );

        service.delete( config );
        Assert.assertEquals( 1, service.list().size() );

        service.delete( anotherConfig );
        Assert.assertEquals( 0, service.list().size() );
    }
}