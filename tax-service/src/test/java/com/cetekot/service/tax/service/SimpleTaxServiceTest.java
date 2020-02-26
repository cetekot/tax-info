package com.cetekot.service.tax.service;

import com.cetekot.service.tax.dto.SimpleTaxRequestDto;
import com.cetekot.service.tax.dto.SimpleTaxResponseDto;
import com.cetekot.service.tax.persistence.entity.*;
import com.cetekot.service.tax.persistence.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright:    Copyright (c) 2020
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application.properties" )
class SimpleTaxServiceTest {

    private final String TAXPAYER_ID = UUID.randomUUID().toString();

    @Autowired
    private SimpleTaxService service;

    @Autowired
    private IncomeDataRepository incomeDataRepository;

    @Autowired
    private TaxTypeRepository taxTypeRepository;

    @Autowired
    private TaxLayerRepository taxLayerRepository;

    @Autowired
    private TaxDataRepository taxDataRepository;

    @BeforeEach
    public void setUp() {

        // Current income for year 2020: 1510
        incomeDataRepository.save( new IncomeData( null, TAXPAYER_ID, java.sql.Date.valueOf( LocalDate.of( 2020, 1, 22 ) ), 826d ) );
        incomeDataRepository.save( new IncomeData( null, TAXPAYER_ID, java.sql.Date.valueOf( LocalDate.of( 2020, 2, 10 ) ), 684d ) );

        // Tax layers:
        // 0-600: 10%
        // 601-1200: 15%
        // 1201-1600: 20%
        // 1601+: 30%
        TaxType type = new TaxType( null, "Test type", "Test Description" );
        taxTypeRepository.save( type );

        // Total paid already for year 2020: 167.85
        taxDataRepository.save( new TaxData( null, TAXPAYER_ID, type, java.sql.Date.valueOf( LocalDate.of( 2020, 1, 22 ) ), 67.85d ) );
        taxDataRepository.save( new TaxData( null, TAXPAYER_ID, type, java.sql.Date.valueOf( LocalDate.of( 2020, 2, 22 ) ), 100d ) );

        final Calendar validFrom = Calendar.getInstance();
        validFrom.set( 2020, 1, 1 );
        final Calendar validTo = Calendar.getInstance();
        validTo.set( 2020, 12, 31 );

        taxLayerRepository.save( new TaxLayer( null, type, 10d, 0d, 600d, validFrom.getTime(), validTo.getTime() ) );
        taxLayerRepository.save( new TaxLayer( null, type, 15d, 600.01d, 1200d, validFrom.getTime(), validTo.getTime() ) );
        taxLayerRepository.save( new TaxLayer( null, type, 20d, 1200.01d, 1600d, validFrom.getTime(), validTo.getTime() ) );
        taxLayerRepository.save( new TaxLayer( null, type, 30d, 1600.01d, Double.MAX_VALUE, validFrom.getTime(), validTo.getTime() ) );
    }

    @Test
    void calculate() {

        final double amountReceived = 935.34d;
        final Calendar c = Calendar.getInstance();
        c.set( 2020, 3, 18 );

        SimpleTaxRequestDto dto = new SimpleTaxRequestDto( TAXPAYER_ID, amountReceived, c.getTime(), true );
        SimpleTaxResponseDto result = service.calculate( dto );

        assertEquals( TAXPAYER_ID, result.getTaxpayerId() );
        assertEquals( 315.749d, result.getTaxPayable() );
    }
}