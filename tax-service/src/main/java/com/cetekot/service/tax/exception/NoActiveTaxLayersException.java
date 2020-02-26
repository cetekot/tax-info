package com.cetekot.service.tax.exception;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class NoActiveTaxLayersException extends RuntimeException {

    public NoActiveTaxLayersException() {

    }

    public NoActiveTaxLayersException( String message ) {

        super( message );
    }

    public NoActiveTaxLayersException( String message, Throwable cause ) {

        super( message, cause );
    }

    public NoActiveTaxLayersException( Throwable cause ) {

        super( cause );
    }
}
