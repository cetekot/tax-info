package com.cetekot.service.authentication.dto;

import lombok.Data;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
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
