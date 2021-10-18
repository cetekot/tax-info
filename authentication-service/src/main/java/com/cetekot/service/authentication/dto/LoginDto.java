package com.cetekot.service.authentication.dto;

import lombok.Data;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Data
public class LoginDto {

    private final String username;
    private final String password;
}
