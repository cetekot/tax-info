package com.cetekot.service.tax.persistence.repository;

import com.cetekot.service.tax.persistence.entity.IncomeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public interface IncomeDataRepository extends JpaRepository<IncomeData, String> {

    @Query("SELECT i FROM IncomeData i WHERE YEAR(i.receivedAt) = ?1")
    List<IncomeData> findAllForSpecifiedYear( int year );
}
