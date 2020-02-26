package com.cetekot.service.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@EnableConfigServer
@SpringBootApplication
@EnableEurekaClient
public class ConfigServiceApplication {

    public static void main( String[] args ) {

        SpringApplication.run( ConfigServiceApplication.class, args );
    }
}
