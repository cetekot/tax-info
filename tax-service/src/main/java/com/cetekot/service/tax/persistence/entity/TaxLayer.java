package com.cetekot.service.tax.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Copyright:    Copyright (c) 2020-2021
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
    private BigDecimal percent;

    @Column( name = "min_amount" )
    private BigDecimal minAmount;

    @Column( name = "max_amount" )
    private BigDecimal maxAmount;

    @Column( name = "valid_from" )
    private LocalDateTime validFrom;

    @Column( name = "valid_to" )
    private LocalDateTime validTo;
}
