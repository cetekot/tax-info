package com.cetekot.service.tax.controller;

import com.cetekot.service.tax.dto.SimpleTaxRequestDto;
import com.cetekot.service.tax.dto.SimpleTaxResponseDto;
import com.cetekot.service.tax.service.SimpleTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
@RestController
@RequestMapping( "/api/v1/tax/simple" )
public class SimpleTaxController {

    private SimpleTaxService taxService;

    @Autowired
    public SimpleTaxController( SimpleTaxService taxService ) {

        this.taxService = taxService;
    }

    @PostMapping
    public ResponseEntity<SimpleTaxResponseDto> calculateSimple( @RequestBody SimpleTaxRequestDto dto ) {

        return ResponseEntity.ok( taxService.calculate( dto ) );
    }
}
