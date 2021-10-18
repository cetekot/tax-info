package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.UserNotFoundException;
import com.cetekot.service.authentication.persistence.entity.User;
import com.cetekot.service.authentication.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService( UserRepository repository ) {

        this.repository = repository;
    }

    public User findByUsername( String id ) {

        if( id != null && !"".equals( id ) ) {

            Optional<User> result = repository.findById( id );
            if( result.isPresent() ) {

                return result.get();
            }
        }

        throw new UserNotFoundException( id, null );
    }

    public User findByUsernameAndPassword( String username, String password ) {

        Optional<User> founded = repository.findByUsernameAndPassword( username, password );
        if( founded.isPresent() ) {

            return founded.get();
        }

        throw new UserNotFoundException( username, password );
    }

    public void save( User entity ) {

        repository.save( entity );
    }

    public void delete( User entity ) {

        repository.delete( entity );
    }

    public List<User> list() {

        return repository.findAll();
    }
}
