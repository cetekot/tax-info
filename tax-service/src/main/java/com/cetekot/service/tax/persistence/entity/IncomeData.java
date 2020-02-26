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
@Table( name = "income_data" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeData {

    @Id
    @Column( name = "id" )
    @GeneratedValue( generator = "system-uuid" )
    @GenericGenerator( name = "system-uuid", strategy = "uuid2" )
    private String id;

    @Column( name = "tax_payer_id", nullable = false )
    private String taxpayerId;

    @Column( name = "received_at", nullable = false )
    private Date receivedAt;

    @Column( name = "amount", nullable = false )
    private Double amount;
}
