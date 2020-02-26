package com.cetekot.service.tax.security;

import com.cetekot.service.auth.AuthenticationClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationClient authenticationClient;

    @Autowired
    public TokenAuthenticationFilter( AuthenticationClient authenticationClient ) {

        this.authenticationClient = authenticationClient;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) {

        try {

            final String accessToken = request.getHeader( "AuthToken" );

            if( accessToken == null ) {

                response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
                return;
            }

            try {

                authenticationClient.checkToken( accessToken );
            }
            catch( FeignException.Unauthorized ex ) {

                log.warn( "Unauthorized access with token {}", accessToken );
                response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
                return;
            }

            filterChain.doFilter( request, response );
        }
        catch( Exception e ) {

            log.error( "Unexpected exception in TokenAuthenticationFilter", e );
            if( !response.isCommitted() ) {

                response.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            }
        }
    }
}
