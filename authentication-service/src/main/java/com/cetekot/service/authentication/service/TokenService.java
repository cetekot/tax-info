package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.TokenNotFoundException;
import com.cetekot.service.authentication.persistence.entity.Token;
import com.cetekot.service.authentication.persistence.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Service
public class TokenService {

    private final TokenRepository repository;

    @Autowired
    public TokenService( TokenRepository repository ) {

        this.repository = repository;
    }

    public Token findById( String id ) {

        if( id != null && !"".equals( id ) ) {

            Optional<Token> result = repository.findById( id );
            if( result.isPresent() ) {

                return result.get();
            }
        }

        throw new TokenNotFoundException( id );
    }

    public void save( Token entity ) {

        repository.save( entity );
    }

    public void delete( Token entity ) {

        repository.delete( entity );
    }

    public List<Token> list() {

        return repository.findAll();
    }

    public boolean isValid( Token token ) {

        return token.getValidTo().isAfter( LocalDateTime.now() );
    }
}
