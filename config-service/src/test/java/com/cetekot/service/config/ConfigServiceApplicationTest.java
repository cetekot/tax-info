package com.cetekot.service.config;

import org.junit.Test;
import org.junit.runner.RunWith;
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
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT )
@TestPropertySource( locations = "classpath:application.properties" )
public class ConfigServiceApplicationTest {

    @Test
    public void contextLoads() {

    }
}