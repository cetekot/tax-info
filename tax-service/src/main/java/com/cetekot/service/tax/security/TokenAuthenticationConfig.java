package com.cetekot.service.tax.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
@Order( 1 )
public class TokenAuthenticationConfig extends WebSecurityConfigurerAdapter {

    private final Environment env;
    private final TokenAuthenticationFilter filter;

    @Autowired
    public TokenAuthenticationConfig( Environment env, TokenAuthenticationFilter filter ) {

        this.env = env;
        this.filter = filter;
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        String[] urls = { "/api/v1/tax/**" };
        http.cors().and().requestMatchers()
                .antMatchers( HttpMethod.GET, urls ).and()
                .addFilterBefore( filter, BasicAuthenticationFilter.class )
                .csrf().disable();

        http.cors().and().requestMatchers()
                .antMatchers( HttpMethod.POST, urls ).and()
                .addFilterBefore( filter, BasicAuthenticationFilter.class )
                .csrf().disable();

        http.cors().and().authorizeRequests().antMatchers( HttpMethod.OPTIONS, urls ).permitAll();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        String[] activeProfiles = env.getActiveProfiles();
        List<String> allowedOrigins = new ArrayList<>();
        if( Arrays.asList( activeProfiles ).contains( "dev" ) ) {

            allowedOrigins.add( "*" );
        }
        else if( Arrays.asList( activeProfiles ).contains( "stage" ) ) {

            allowedOrigins.add( "*" );
        }
        else if( Arrays.asList( activeProfiles ).contains( "prod" ) ) {

            allowedOrigins.add( "my-secret-server.com" );
        }

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins( allowedOrigins );
        configuration.setAllowedHeaders( Collections.singletonList( "*" ) );
        configuration.setAllowedMethods( Arrays.asList( "GET", "POST", "OPTIONS" ) );
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**", configuration );
        return source;
    }
}
