package com.cetekot.service.authentication.exception;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class ConfigNotFoundException extends RuntimeException {

    private final String key;

    public ConfigNotFoundException( String key ) {

        this.key = key;
    }

    public String getKey() {

        return key;
    }
}
