package com.cetekot.service.tax.service;

import com.cetekot.service.tax.dto.SimpleTaxRequestDto;
import com.cetekot.service.tax.dto.SimpleTaxResponseDto;
import com.cetekot.service.tax.exception.NoActiveTaxLayersException;
import com.cetekot.service.tax.persistence.entity.IncomeData;
import com.cetekot.service.tax.persistence.entity.TaxData;
import com.cetekot.service.tax.persistence.entity.TaxLayer;
import com.cetekot.service.tax.persistence.repository.IncomeDataRepository;
import com.cetekot.service.tax.persistence.repository.TaxDataRepository;
import com.cetekot.service.tax.persistence.repository.TaxLayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright:    Copyright (c) 2020-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Service
public class SimpleTaxService {

    private final IncomeDataRepository incomeDataRepository;
    private final TaxLayerRepository layerRepository;
    private final TaxDataRepository dataRepository;

    @Autowired
    public SimpleTaxService( IncomeDataRepository incomeDataRepository, TaxLayerRepository layerRepository, TaxDataRepository dataRepository ) {

        this.incomeDataRepository = incomeDataRepository;
        this.layerRepository = layerRepository;
        this.dataRepository = dataRepository;
    }

    public SimpleTaxResponseDto calculate( SimpleTaxRequestDto dto ) {

        List<TaxLayer> activeLayers = layerRepository.findAllActive();
        if( activeLayers.isEmpty() ) {

            throw new NoActiveTaxLayersException();
        }

        List<IncomeData> allIncome = incomeDataRepository.findAllForSpecifiedYear( dto.getReceivedAt().getYear() );
        List<TaxData> allTaxPaid = dataRepository.findAllByTaxpayerId( dto.getTaxpayerId() );

        BigDecimal incomeReceived = allIncome.size() > 0 ? allIncome.stream().map( IncomeData::getAmount ).reduce( BigDecimal::add ).get() : BigDecimal.ZERO;
        BigDecimal taxSum = allTaxPaid.size() > 0 ? allTaxPaid.stream().map( TaxData::getAmount ).reduce( BigDecimal::add ).get() : BigDecimal.ZERO;
        BigDecimal newTax = calculateTax( incomeReceived.add( dto.getIncome() ), activeLayers );

        return new SimpleTaxResponseDto( dto.getTaxpayerId(), newTax.add( taxSum.negate() ) );
    }

    private BigDecimal calculateTax( BigDecimal income, List<TaxLayer> activeLayers ) {

        BigDecimal result = BigDecimal.ZERO;
        BigDecimal lastMax = BigDecimal.ZERO;

        for( TaxLayer layer : activeLayers ) {

            if( income.compareTo( layer.getMinAmount() ) > 0 ) {

                if( income.compareTo( layer.getMaxAmount() ) >= 0 ) {

                    // full layer
                    result = result.add( ( layer.getMaxAmount().add( lastMax.negate() ) ).multiply( layer.getPercent() ).divide( new BigDecimal( 100 ) ) );
                }
                else {

                    // less than max, more than min
                    result = result.add( ( income.add( layer.getMinAmount().negate() ) ).multiply( layer.getPercent() ).divide( new BigDecimal( 100 ) ) );
                }
            }

            lastMax = layer.getMaxAmount();
        }

        return result;
    }
}
