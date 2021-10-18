package com.cetekot.service.tax.persistence.repository;

import com.cetekot.service.tax.persistence.entity.TaxLayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Copyright:    Copyright (c) 2020-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public interface TaxLayerRepository extends JpaRepository<TaxLayer, String> {

    @Query("SELECT t FROM TaxLayer t WHERE t.validFrom <= CURRENT_TIMESTAMP AND t.validTo >= CURRENT_TIMESTAMP ORDER BY t.minAmount")
    List<TaxLayer> findAllActive();
}
