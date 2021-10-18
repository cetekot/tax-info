package com.cetekot.service.authentication.dto;

import lombok.Data;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Data
public class RestErrorDto {

    private final String code;
    private final String message;
    private final String httpCode;
}
