package com.ixaris.interview.transfers;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Account {
    @NonNull
    private Long number;
    @NonNull
    private BigDecimal balance;
    private int useAsSourceCount;

    public void Account(final Long number, final BigDecimal initialBalance) {
        this.number = number;
        balance = initialBalance;
        useAsSourceCount = 0;
    }

    public void addAmount(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void subtractAmount(BigDecimal amount) {
        balance = balance.subtract(amount);
        useAsSourceCount++;
    }

    public String toString() {
        return number + " - " + balance;
    }
}
