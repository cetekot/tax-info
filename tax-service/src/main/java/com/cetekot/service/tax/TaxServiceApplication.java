package com.cetekot.service.tax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@SpringBootApplication( exclude = { SecurityAutoConfiguration.class } )
@EntityScan( basePackages = { "com.cetekot.service.tax.persistence.entity" } )
@EnableJpaRepositories( basePackages = { "com.cetekot.service.tax.persistence.repository" } )
@EnableFeignClients
public class TaxServiceApplication {

    public static void main( String[] args ) {

        SpringApplication.run( TaxServiceApplication.class, args );
    }
}
