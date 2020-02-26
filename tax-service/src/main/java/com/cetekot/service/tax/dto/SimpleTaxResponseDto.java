package com.cetekot.service.tax.dto;

import lombok.*;

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
public class SimpleTaxResponseDto {

    private String taxpayerId;
    private Double taxPayable;
}
