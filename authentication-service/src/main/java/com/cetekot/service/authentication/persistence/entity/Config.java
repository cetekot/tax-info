package com.cetekot.service.authentication.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Entity
@Table( name = "configurations" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {

    public static final String TOKEN_INTERVAL = "token.expiration.interval";

    @Id
    @Column( name = "config_key", nullable = false )
    private String key;

    @Column( name = "config_value", nullable = false )
    private String value;
}
