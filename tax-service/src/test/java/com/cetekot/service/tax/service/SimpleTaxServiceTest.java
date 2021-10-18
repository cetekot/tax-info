package com.cetekot.service.tax.service;

import com.cetekot.service.tax.dto.SimpleTaxRequestDto;
import com.cetekot.service.tax.dto.SimpleTaxResponseDto;
import com.cetekot.service.tax.persistence.entity.IncomeData;
import com.cetekot.service.tax.persistence.entity.TaxData;
import com.cetekot.service.tax.persistence.entity.TaxLayer;
import com.cetekot.service.tax.persistence.entity.TaxType;
import com.cetekot.service.tax.persistence.repository.IncomeDataRepository;
import com.cetekot.service.tax.persistence.repository.TaxDataRepository;
import com.cetekot.service.tax.persistence.repository.TaxLayerRepository;
import com.cetekot.service.tax.persistence.repository.TaxTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Copyright:    Copyright (c) 2020-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
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
        incomeDataRepository.save( new IncomeData( null, TAXPAYER_ID, LocalDateTime.of( 2020, 1, 22, 12, 34 ), new BigDecimal( 826 ) ) );
        incomeDataRepository.save( new IncomeData( null, TAXPAYER_ID, LocalDateTime.of( 2020, 2, 10, 13, 48 ), new BigDecimal( 684 ) ) );

        // Tax layers:
        // 0-600: 10%
        // 601-1200: 15%
        // 1201-1600: 20%
        // 1601+: 30%
        TaxType type = new TaxType( null, "Test type", "Test Description" );
        taxTypeRepository.save( type );

        // Total paid already for year 2020: 167.85
        taxDataRepository.save( new TaxData( null, TAXPAYER_ID, type, LocalDateTime.of( 2020, 1, 22, 11, 21 ), new BigDecimal( "67.85" ) ) );
        taxDataRepository.save( new TaxData( null, TAXPAYER_ID, type, LocalDateTime.of( 2020, 2, 22, 12, 22 ), new BigDecimal( 100 ) ) );

        final LocalDateTime validFrom = LocalDateTime.now().minusYears( 1 );
        final LocalDateTime validTo = LocalDateTime.now().plusYears( 1 );

        taxLayerRepository.save( new TaxLayer( null, type, new BigDecimal( 10 ), BigDecimal.ZERO, new BigDecimal( 600 ), validFrom, validTo ) );
        taxLayerRepository.save( new TaxLayer( null, type, new BigDecimal( 15 ), new BigDecimal( "600.01" ), new BigDecimal( 1200 ), validFrom, validTo ) );
        taxLayerRepository.save( new TaxLayer( null, type, new BigDecimal( 20 ), new BigDecimal( "1200.01" ), new BigDecimal( 1600 ), validFrom, validTo ) );
        taxLayerRepository.save( new TaxLayer( null, type, new BigDecimal( 30 ), new BigDecimal( "1600.01" ), BigDecimal.valueOf( Integer.MAX_VALUE ), validFrom, validTo ) );
    }

    @Test
    void calculate() {

        final double amountReceived = 935.34d;
        SimpleTaxRequestDto dto = new SimpleTaxRequestDto( TAXPAYER_ID, new BigDecimal( amountReceived ), LocalDateTime.of( 2020, 3, 18, 10, 10 ), true );
        SimpleTaxResponseDto result = service.calculate( dto );

        Assertions.assertEquals( TAXPAYER_ID, result.getTaxpayerId() );
        Assertions.assertEquals( new BigDecimal( "315.749" ), result.getTaxPayable().setScale( 3, RoundingMode.HALF_EVEN ) );
    }
}