package com.cetekot.service.authentication.persistence.repository;

import com.cetekot.service.authentication.persistence.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
}
