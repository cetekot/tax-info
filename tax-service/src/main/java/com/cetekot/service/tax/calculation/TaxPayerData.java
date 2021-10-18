package com.cetekot.service.tax.calculation;

import java.text.MessageFormat;

/**
 * Copyright:    Copyright (c) 2019-2021
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class TaxPayerData {

    private String id;
    private Double netto;
    private Double brutto;

    public TaxPayerData() {

    }

    public TaxPayerData( String id, Double netto, Double brutto ) {

        this.id = id;
        this.netto = netto;
        this.brutto = brutto;
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

    public Double getBrutto() {

        return brutto;
    }

    public void setBrutto( Double brutto ) {

        this.brutto = brutto;
    }

    @Override
    public String toString() {

        return MessageFormat.format( "TaxPayerData [id={0}, netto={1}, brutto={2}]",
                id, netto, brutto );
    }
}
