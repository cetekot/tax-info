package com.cetekot.service.authentication.exception;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException {

    private final String username;
    private final String password;

    public UserNotFoundException( String username, String password ) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }
}
