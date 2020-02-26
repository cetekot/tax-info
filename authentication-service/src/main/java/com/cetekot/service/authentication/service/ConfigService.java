package com.cetekot.service.authentication.service;

import com.cetekot.service.authentication.exception.ConfigNotFoundException;
import com.cetekot.service.authentication.persistence.entity.Config;
import com.cetekot.service.authentication.persistence.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Service
public class ConfigService {

    private ConfigRepository repository;

    @Autowired
    public ConfigService( ConfigRepository repository ) {

        this.repository = repository;
    }

    public Config findById( String id ) {

        if( id != null && !"".equals( id ) ) {

            Optional<Config> result = repository.findById( id );
            if( result.isPresent() ) {

                return result.get();
            }
        }

        throw new ConfigNotFoundException( id );
    }

    public void save( Config entity ) {

        repository.save( entity );
    }

    public void delete( Config entity ) {

        repository.delete( entity );
    }

    public List<Config> list() {

        return repository.findAll();
    }
}
