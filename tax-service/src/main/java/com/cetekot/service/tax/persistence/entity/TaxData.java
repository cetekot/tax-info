package com.cetekot.service.tax.persistence.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Entity
@Table( name = "taxes" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxData {

    @Id
    @Column( name = "id" )
    @GeneratedValue( generator = "system-uuid" )
    @GenericGenerator( name = "system-uuid", strategy = "uuid2" )
    private String id;

    @Column( name = "tax_payer_id", nullable = false )
    private String taxpayerId;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "type_id" )
    private TaxType type;

    @Column( name = "payed_at", nullable = false )
    private LocalDateTime payedAt;

    @Column( name = "amount", nullable = false )
    private BigDecimal amount;
}
