package com.ixaris.interview.transfers.domain;

import java.util.*;

public class Ixaris {

    private Map<Long, Account> accounts = new HashMap<>();

    /**
     * Build a map of Accounts with the change of balance depending of the transfers amount
     *
     * @param transfers List of Transfers to map as Account list
     */
    public Ixaris(List<Transfer> transfers) {
        for (Transfer transfer : transfers) {
            addTransfer(transfer);
        }
    }

    public void addTransfer(Transfer transfer) {
        if (transfer.getSourceAcct() != 0) {
            Account account;
            if (accounts.containsKey(transfer.getSourceAcct())) {
                account = accounts.get(transfer.getSourceAcct());
                account.subtractAmount(transfer.getAmount());
            } else {
                account = new Account(transfer.getSourceAcct(), transfer.getAmount());
            }
            accounts.put(account.getNumber(), account);
        }
        Account account;
        if (accounts.containsKey(transfer.getDestinationAcct())) {
            account = accounts.get(transfer.getDestinationAcct());
            account.addAmount(transfer.getAmount());
        } else {
            account = new Account(transfer.getDestinationAcct(), transfer.getAmount());
        }
        accounts.put(account.getNumber(), account);
    }

    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    /**
     * @return Account with the highest balance
     */
    public Account getAccountHighestBalance() {
        return getAccountHighest(Comparator.comparing(Account::getBalance));
    }

    /**
     * @return Account frequently used source
     */
    public Account getAccountFrequentlyUsedSource() {
        return getAccountHighest(Comparator.comparing(Account::getUseAsSourceCount));
    }

    /**
     * @param comparing Comparator field to obtain the maximum
     * @return Account which contains the maximum field depending on the comparator
     */
    public Account getAccountHighest(Comparator<Account> comparing) {
        return accounts.values().stream()
                .max(comparing)
                .orElseThrow(NoSuchElementException::new);
    }
}
