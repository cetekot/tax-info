package com.cetekot.service.authentication;

import com.cetekot.service.authentication.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Service
public class TestCleanup {

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public void performCleanup() {

        userRepository.deleteAll();
        tokenRepository.deleteAll();
        configRepository.deleteAll();
    }
}
