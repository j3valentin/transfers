package com.ixaris.interview.transfers.cli;

import com.ixaris.interview.transfers.domain.Transfer;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class Transfer maps a CSV line
 *
 * @author johard
 */
public final class TransferCSV extends Transfer {
    /**
     * Date format used in the CSV line
     */
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    @CsvBindByPosition(position = 0)
    protected Long sourceAcct;

    @CsvBindByPosition(position = 1)
    protected Long destinationAcct;

    @CsvBindByPosition(position = 2)
    protected BigDecimal amount;

    @CsvBindByPosition(position = 3)
    @CsvDate(DATE_FORMAT)
    protected Date date;

    @CsvBindByPosition(position = 4)
    protected int transferId;
}
