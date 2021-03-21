package com.ixaris.interview.transfers.domain;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * The Class Account manage the balance and the counter of uses as source account
 *
 * @author johard
 */
@Data
public class Account {
    @NonNull
    protected final Long number;
    @NonNull
    protected BigDecimal balance;
    protected int useAsSourceCount = 0;

    /**
     * @param amount Amount to add to the balance
     */
    public void addAmount(BigDecimal amount) {
        balance = balance.add(amount);
    }

    /**
     * Subtract amount also increases the counter as a source account
     *
     * @param amount Amount to subtract to the balance
     */
    public void subtractAmount(BigDecimal amount) {
        balance = balance.subtract(amount);
        useAsSourceCount++;
    }

    public String toString() {
        return number + " - " + balance;
    }
}
