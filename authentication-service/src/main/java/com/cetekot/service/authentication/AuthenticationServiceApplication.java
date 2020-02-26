package com.cetekot.service.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@SpringBootApplication( exclude = { SecurityAutoConfiguration.class } )
@EntityScan( basePackages = { "com.cetekot.service.authentication.persistence.entity" } )
@EnableJpaRepositories( basePackages = { "com.cetekot.service.authentication.persistence.repository" } )
@EnableEurekaClient
public class AuthenticationServiceApplication {

    public static void main( String[] args ) {

        SpringApplication.run( AuthenticationServiceApplication.class, args );
    }
}
