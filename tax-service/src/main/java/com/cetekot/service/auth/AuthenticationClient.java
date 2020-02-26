package com.cetekot.service.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
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
