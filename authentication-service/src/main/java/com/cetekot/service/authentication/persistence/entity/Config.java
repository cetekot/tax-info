package com.cetekot.service.authentication.persistence.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
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
