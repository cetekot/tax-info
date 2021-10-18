package com.cetekot.service.tax.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@Order( 2 )
public class NoAuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        http.headers().frameOptions().disable();
    }
}
