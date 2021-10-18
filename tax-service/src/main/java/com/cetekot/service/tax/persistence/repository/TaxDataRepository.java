package com.cetekot.service.tax.persistence.repository;

import com.cetekot.service.tax.persistence.entity.TaxData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Copyright:    Copyright (c) 2020-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public interface TaxDataRepository extends JpaRepository<TaxData, String> {

    @Query("SELECT d FROM TaxData d WHERE d.taxpayerId = ?1")
    List<TaxData> findAllByTaxpayerId( String taxpayerId );
}
