package com.cetekot.service.tax.calculation;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public interface TaxCalculator {

    TaxCalculationResult calculate( TaxPayerData data );
}
