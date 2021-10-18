package com.cetekot.service.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@FeignClient( name = "AUTHENTICATION-SERVICE" )
public interface AuthenticationClient {

    @GetMapping( "/api/v1/auth/{token}" )
    @ResponseBody
    String checkToken( @PathVariable String token );
}
