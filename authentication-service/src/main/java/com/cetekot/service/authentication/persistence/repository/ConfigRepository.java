package com.cetekot.service.authentication.persistence.repository;

import com.cetekot.service.authentication.persistence.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Repository
public interface ConfigRepository extends JpaRepository<Config, String> {
}
