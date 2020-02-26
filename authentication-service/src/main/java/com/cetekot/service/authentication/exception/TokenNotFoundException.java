package com.cetekot.service.authentication.exception;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class TokenNotFoundException extends RuntimeException {

    private final String token;

    public TokenNotFoundException( String token ) {

        this.token = token;
    }

    public String getToken() {

        return token;
    }
}
