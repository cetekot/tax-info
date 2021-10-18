package com.cetekot.service.authentication.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Entity
@Table( name = "tokens" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    @Column( name = "token", nullable = false )
    private String token;

    @Column( name = "valid_to", nullable = false )
    private LocalDateTime validTo;
}
