package com.cetekot.service.tax.calculation;

import java.text.MessageFormat;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class TaxCalculationResult {

    private String id;
    private Double netto;
    private Double socialTaxPersonal;
    private Double incomeTaxPersonal;
    private Double socialTaxEmployer;
    private Double incomeTaxEmployer;

    public TaxCalculationResult() {

    }

    public TaxCalculationResult( String id, Double netto, Double socialTaxPersonal, Double incomeTaxPersonal, Double socialTaxEmployer, Double incomeTaxEmployer ) {

        this.id = id;
        this.netto = netto;
        this.socialTaxPersonal = socialTaxPersonal;
        this.incomeTaxPersonal = incomeTaxPersonal;
        this.socialTaxEmployer = socialTaxEmployer;
        this.incomeTaxEmployer = incomeTaxEmployer;
    }

    public String getId() {

        return id;
    }

    public void setId( String id ) {

        this.id = id;
    }

    public Double getNetto() {

        return netto;
    }

    public void setNetto( Double netto ) {

        this.netto = netto;
    }

    public Double getSocialTaxPersonal() {

        return socialTaxPersonal;
    }

    public void setSocialTaxPersonal( Double socialTaxPersonal ) {

        this.socialTaxPersonal = socialTaxPersonal;
    }

    public Double getIncomeTaxPersonal() {

        return incomeTaxPersonal;
    }

    public void setIncomeTaxPersonal( Double incomeTaxPersonal ) {

        this.incomeTaxPersonal = incomeTaxPersonal;
    }

    public Double getSocialTaxEmployer() {

        return socialTaxEmployer;
    }

    public void setSocialTaxEmployer( Double socialTaxEmployer ) {

        this.socialTaxEmployer = socialTaxEmployer;
    }

    public Double getIncomeTaxEmployer() {

        return incomeTaxEmployer;
    }

    public void setIncomeTaxEmployer( Double incomeTaxEmployer ) {

        this.incomeTaxEmployer = incomeTaxEmployer;
    }

    @Override
    public String toString() {

        return MessageFormat.format( "TaxCalculationResult [id={0}, netto={1}, socialTaxPersonal={2}, incomeTaxPersonal={3}, " +
                        "socialTaxEmployer={4}, incomeTaxEmployer={5}]",
                id, netto, socialTaxPersonal, incomeTaxPersonal, socialTaxEmployer, incomeTaxEmployer );
    }
}
