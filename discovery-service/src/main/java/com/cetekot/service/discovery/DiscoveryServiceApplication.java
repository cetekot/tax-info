package com.cetekot.service.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServiceApplication {

    public static void main( String[] args ) {

        SpringApplication.run( DiscoveryServiceApplication.class, args );
    }
}
