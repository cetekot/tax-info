package com.cetekot.service.tax.calculation;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Copyright:    Copyright (c) 2019
 * Company:      Crazy coding inc.
 *
 * @author Andrei 'cetekot' Larin
 * @version 1.0
 */
public class DateKey {

    private Date startDate;
    private Date endDate;

    public DateKey() {

    }

    public DateKey( Date startDate, Date endDate ) {

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {

        return startDate;
    }

    public void setStartDate( Date startDate ) {

        this.startDate = startDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setEndDate( Date endDate ) {

        this.endDate = endDate;
    }

    @Override
    public String toString() {

        return MessageFormat.format( "DateKey [startDate={0}, endDate={1}]", startDate, endDate );
    }
}
