package com.cetekot.service.tax.dto;

import lombok.*;

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
    private Double income;
    private Date receivedAt;
    private boolean isChecking;
}
