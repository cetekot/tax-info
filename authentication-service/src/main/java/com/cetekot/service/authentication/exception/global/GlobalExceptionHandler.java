package com.cetekot.service.authentication.exception.global;

import com.cetekot.service.authentication.dto.RestErrorDto;
import com.cetekot.service.authentication.exception.ConfigNotFoundException;
import com.cetekot.service.authentication.exception.TokenNotFoundException;
import com.cetekot.service.authentication.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( ConfigNotFoundException.class )
    @ResponseStatus( HttpStatus.NOT_FOUND )
    public RestErrorDto consumedFoodNotFound( ConfigNotFoundException e ) {

        log.warn( "No config found for key {}", e.getKey() );
        return new RestErrorDto( "ERROR-CONFIG-001", MessageFormat.format( "No config found for key {0}", e.getKey() ), "404" );
    }

    @ExceptionHandler( TokenNotFoundException.class )
    @ResponseStatus( HttpStatus.UNAUTHORIZED )
    public RestErrorDto dailySummaryNotFound( TokenNotFoundException e ) {

        log.warn( "No token found for name {}", e.getToken() );
        return new RestErrorDto( "ERROR-TOKEN-001", MessageFormat.format( "No token found for name {0}", e.getToken() ), "404" );
    }

    @ExceptionHandler( UserNotFoundException.class )
    @ResponseStatus( HttpStatus.NOT_FOUND )
    public RestErrorDto genericBadRequest( UserNotFoundException e ) {

        log.warn( "No user found for username {} and password {}", e.getUsername(), e.getPassword() );
        return new RestErrorDto( "ERROR-USER-001", MessageFormat.format( "No user found for username {0} and password {1}",
                e.getUsername(), e.getPassword() ), "404" );
    }

    @ExceptionHandler( Exception.class )
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    public RestErrorDto allOtherExceptions( Exception e ) {

        log.error( "The application encountered the following exception", e );
        return new RestErrorDto( "ERROR-COMMON-500", "Internal Server Error, please contact service provider if error persists", "500" );
    }
}
