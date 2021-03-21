package com.ixaris.interview.transfers.rest;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * The Class Account manage the balance and the counter of uses as source account
 *
 * @author johard
 */
@Data
@Entity
@NoArgsConstructor
public class Account {
    @Id
    @NonNull
    private Long number;
    private BigDecimal balance = BigDecimal.ZERO;
    private int useAsSourceCount = 0;

    public Account(Long number) {
        this.number = number;
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
