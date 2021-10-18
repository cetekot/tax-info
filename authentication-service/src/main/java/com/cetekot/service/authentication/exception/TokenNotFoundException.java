package com.cetekot.service.authentication.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public class TokenNotFoundException extends RuntimeException {

    private final String token;
}
