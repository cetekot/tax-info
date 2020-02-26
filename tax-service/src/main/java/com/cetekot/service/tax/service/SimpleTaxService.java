package com.cetekot.service.tax.service;

import com.cetekot.service.tax.dto.SimpleTaxRequestDto;
import com.cetekot.service.tax.dto.SimpleTaxResponseDto;
import com.cetekot.service.tax.exception.NoActiveTaxLayersException;
import com.cetekot.service.tax.persistence.entity.*;
import com.cetekot.service.tax.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@Service
public class SimpleTaxService {

    private IncomeDataRepository incomeDataRepository;
    private TaxLayerRepository layerRepository;
    private TaxDataRepository dataRepository;

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

        LocalDate dateRequested = dto.getReceivedAt().toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        List<IncomeData> allIncome = incomeDataRepository.findAllForSpecifiedYear( dateRequested.getYear() );
        List<TaxData> allTaxPaid = dataRepository.findAllByTaxpayerId( dto.getTaxpayerId() );

        Double incomeReceived = allIncome.stream().mapToDouble( IncomeData::getAmount ).sum();
        Double taxSum = allTaxPaid.stream().mapToDouble( TaxData::getAmount ).sum();
        Double newTax = calculateTax( incomeReceived + dto.getIncome(), activeLayers );

        return new SimpleTaxResponseDto( dto.getTaxpayerId(), newTax - taxSum );
    }

    private Double calculateTax( double income, List<TaxLayer> activeLayers ) {

        Double result = 0d;
        Double lastMax = 0d;

        for( TaxLayer layer : activeLayers ) {

            if( income > layer.getMinAmount() ) {

                if( income >= layer.getMaxAmount() ) {

                    // full layer
                    result += ( layer.getMaxAmount() - lastMax ) * layer.getPercent() / 100d;
                }
                else {

                    // less than max, more than min
                    result += ( income - layer.getMinAmount() ) * layer.getPercent() / 100d;
                }
            }

            lastMax = layer.getMaxAmount();
        }

        return result;
    }
}
