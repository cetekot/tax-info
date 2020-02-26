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
@Table( name = "users" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column( name = "username", nullable = false )
    private String username;

    @Column( name = "password", nullable = false )
    private String password;
}
