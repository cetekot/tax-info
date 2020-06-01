package com.cetekot.service.tax.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTaxRequestDto {

    private String taxpayerId;
    private BigDecimal income;
    private Date receivedAt;
    private boolean isChecking;
}
