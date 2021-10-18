package com.cetekot.service.authentication.persistence.repository;

import com.cetekot.service.authentication.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query( "SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2" )
    Optional<User> findByUsernameAndPassword( String username, String password );
}
