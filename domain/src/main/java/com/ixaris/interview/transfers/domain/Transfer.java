package com.ixaris.interview.transfers.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class Transfer maps a CSV line
 *
 * @author johard
 */
@Data
@NoArgsConstructor
public class Transfer implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Long sourceAcct;
    protected Long destinationAcct;
    protected BigDecimal amount;

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
}
