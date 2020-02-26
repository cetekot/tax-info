package com.cetekot.service.tax.persistence.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Entity
@Table( name = "tax_types" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxType {

    @Id
    @Column( name = "id" )
    @GeneratedValue( generator = "system-uuid" )
    @GenericGenerator( name = "system-uuid", strategy = "uuid2" )
    private String id;

    @Column( name = "title", nullable = false )
    private String title;

    @Column( name = "description" )
    private String description;
}
