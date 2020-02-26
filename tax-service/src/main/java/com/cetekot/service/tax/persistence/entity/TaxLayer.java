package com.cetekot.service.tax.persistence.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Entity
@Table( name = "tax_layers" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxLayer {

    @Id
    @Column( name = "id" )
    @GeneratedValue( generator = "system-uuid" )
    @GenericGenerator( name = "system-uuid", strategy = "uuid2" )
    private String id;

    @ManyToOne
    @JoinColumn( name = "type_id" )
    private TaxType type;

    @Column( name = "percent" )
    private Double percent;

    @Column( name = "min_amount" )
    private Double minAmount;

    @Column( name = "max_amount" )
    private Double maxAmount;

    @Column( name = "valid_from" )
    private Date validFrom;

    @Column( name = "valid_to" )
    private Date validTo;
}
