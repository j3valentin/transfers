package com.ixaris.interview.transfers;

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
    private Long number;
    @NonNull
    private BigDecimal balance;
    private int useAsSourceCount;

    /**
     * Constructor used in the Transfer process
     *
     * @param number         Account number
     * @param initialBalance Amount to be used as initial balance
     */
    public void Account(final Long number, final BigDecimal initialBalance) {
        this.number = number;
        balance = initialBalance;
        useAsSourceCount = 0;
    }

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
