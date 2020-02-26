package com.cetekot.service.authentication.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
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
    private Date validTo;
}
