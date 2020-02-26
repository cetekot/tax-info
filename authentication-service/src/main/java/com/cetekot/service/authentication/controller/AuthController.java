package com.cetekot.service.authentication.controller;

import com.cetekot.service.authentication.dto.LoginDto;
import com.cetekot.service.authentication.persistence.entity.Config;
import com.cetekot.service.authentication.persistence.entity.Token;
import com.cetekot.service.authentication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@RestController
@RequestMapping( "/api/v1/auth" )
public class AuthController {

    private ConfigService configService;
    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public AuthController( ConfigService configService, UserService userService, TokenService tokenService ) {

        this.configService = configService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<String> authenticate( @RequestBody LoginDto loginDto ) {

        if( userService.findByUsernameAndPassword( loginDto.getUsername(), loginDto.getPassword() ) != null ) {

            String token = UUID.randomUUID().toString();
            LocalDateTime validTo = LocalDateTime.now();
            int interval = Integer.parseInt( configService.findById( Config.TOKEN_INTERVAL ).getValue() );
            validTo = validTo.plus( interval, ChronoUnit.SECONDS );
            tokenService.save( new Token( token, Date.from( validTo.atZone( ZoneId.systemDefault() ).toInstant() ) ) );
            return ResponseEntity.ok( token );
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping( value = "/{token}" )
    public ResponseEntity<String> checkToken( @PathVariable String token ) {

        Token dbToken = tokenService.findById( token );
        if( tokenService.isValid( dbToken ) ) {

            return ResponseEntity.ok().body( HttpStatus.OK.toString() );
        }

        return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( HttpStatus.UNAUTHORIZED.toString() );
    }
}
