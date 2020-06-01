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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        incomeDataRepository.save( new IncomeData( null, TAXPAYER_ID, java.sql.Date.valueOf( LocalDate.of( 2020, 1, 22 ) ), new BigDecimal( 826 ) ) );
        incomeDataRepository.save( new IncomeData( null, TAXPAYER_ID, java.sql.Date.valueOf( LocalDate.of( 2020, 2, 10 ) ), new BigDecimal( 684 ) ) );

        // Tax layers:
        // 0-600: 10%
        // 601-1200: 15%
        // 1201-1600: 20%
        // 1601+: 30%
        TaxType type = new TaxType( null, "Test type", "Test Description" );
        taxTypeRepository.save( type );

        // Total paid already for year 2020: 167.85
        taxDataRepository.save( new TaxData( null, TAXPAYER_ID, type, java.sql.Date.valueOf( LocalDate.of( 2020, 1, 22 ) ), new BigDecimal( "67.85" ) ) );
        taxDataRepository.save( new TaxData( null, TAXPAYER_ID, type, java.sql.Date.valueOf( LocalDate.of( 2020, 2, 22 ) ), new BigDecimal( 100 ) ) );

        final Calendar validFrom = Calendar.getInstance();
        validFrom.set( 2020, 1, 1 );
        final Calendar validTo = Calendar.getInstance();
        validTo.set( 2020, 12, 31 );

        taxLayerRepository.save( new TaxLayer( null, type, new BigDecimal( 10 ), BigDecimal.ZERO, new BigDecimal( 600 ), validFrom.getTime(), validTo.getTime() ) );
        taxLayerRepository.save( new TaxLayer( null, type, new BigDecimal( 15 ), new BigDecimal( "600.01" ), new BigDecimal( 1200 ), validFrom.getTime(), validTo.getTime() ) );
        taxLayerRepository.save( new TaxLayer( null, type, new BigDecimal( 20 ), new BigDecimal( "1200.01" ), new BigDecimal( 1600 ), validFrom.getTime(), validTo.getTime() ) );
        taxLayerRepository.save( new TaxLayer( null, type, new BigDecimal( 30 ), new BigDecimal( "1600.01" ), BigDecimal.valueOf( Integer.MAX_VALUE ), validFrom.getTime(), validTo.getTime() ) );
    }

    @Test
    void calculate() {

        final double amountReceived = 935.34d;
        final Calendar c = Calendar.getInstance();
        c.set( 2020, 3, 18 );

        SimpleTaxRequestDto dto = new SimpleTaxRequestDto( TAXPAYER_ID, new BigDecimal( amountReceived ), c.getTime(), true );
        SimpleTaxResponseDto result = service.calculate( dto );

        assertEquals( TAXPAYER_ID, result.getTaxpayerId() );
        assertEquals( new BigDecimal( "315.749" ), result.getTaxPayable().setScale( 3, RoundingMode.HALF_EVEN ) );
    }
}