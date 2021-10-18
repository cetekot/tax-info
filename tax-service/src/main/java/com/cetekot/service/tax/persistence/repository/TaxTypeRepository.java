package com.cetekot.service.tax.persistence.repository;

import com.cetekot.service.tax.persistence.entity.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright:    Copyright (c) 2020-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public interface TaxTypeRepository extends JpaRepository<TaxType, String> {

}
