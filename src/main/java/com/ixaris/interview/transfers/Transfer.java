package com.ixaris.interview.transfers;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class Transfer maps a CSV line
 *
 * @author johard
 */
@Data
@NoArgsConstructor
public class Transfer {
    /**
     * Date format used in the CSV line
     */
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Constructor used in the tests
     *
     * @param sourceAcct      Account source number
     * @param destinationAcct Account destination number
     * @param amount          Transfer's Amount
     */
    public Transfer(final Long sourceAcct, final Long destinationAcct, final BigDecimal amount) {
        this.sourceAcct = sourceAcct;
        this.destinationAcct = destinationAcct;
        this.amount = amount;
    }

    @CsvBindByPosition(position = 0)
    private Long sourceAcct;

    @CsvBindByPosition(position = 1)
    private Long destinationAcct;

    @CsvBindByPosition(position = 2)
    private BigDecimal amount;

    @CsvBindByPosition(position = 3)
    @CsvDate(DATE_FORMAT)
    private Date date;

    @CsvBindByPosition(position = 4)
    private int transferId;
}
