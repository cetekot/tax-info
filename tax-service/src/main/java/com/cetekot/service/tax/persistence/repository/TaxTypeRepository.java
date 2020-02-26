package com.cetekot.service.tax.persistence.repository;

import com.cetekot.service.tax.persistence.entity.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public interface TaxTypeRepository extends JpaRepository<TaxType, String> {

}
