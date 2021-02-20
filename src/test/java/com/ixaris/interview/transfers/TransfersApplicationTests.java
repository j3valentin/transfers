package com.ixaris.interview.transfers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class TransfersApplicationTests {

    @Test
    void contextLoads() {
    }

    private Map<Long, Account> getAccounts() {
        List<Transfer> transfers = new ArrayList<>(Arrays.asList(
                new Transfer(0l, 112233l, new BigDecimal(60.00)),
                new Transfer(0l, 223344l, new BigDecimal(25.03)),
                new Transfer(0l, 334455l, new BigDecimal(67.67)),
                new Transfer(112233l, 223344l, new BigDecimal(11.11)),
                new Transfer(112233l, 334455l, new BigDecimal(12.12)),
                new Transfer(223344l, 334455l, new BigDecimal(006.018))
        ));
        return TransfersApplication.getAccounts(transfers);
    }

    @Test
    public void givenTransferList_thenObtainAccounts() {
        Map<Long, Account> accountsFromTransfers = getAccounts();

        Map<Long, Account> accounts = new HashMap<>() {{
            put(112233l, new Account(112233l, new BigDecimal(36.77)));
            put(223344l, new Account(223344l, new BigDecimal(30.12)));
            put(334455l, new Account(334455l, new BigDecimal(85.81)));
        }};

        assertThat(accountsFromTransfers.size(), is(accounts.size()));
    }

    @Test
    public void givenAccounts_thenObtainAccountHighestBalance() {
        Map<Long, Account> accounts = getAccounts();

        Account accountHighestBalance = TransfersApplication.getAccountHighestBalance(accounts);

        assertThat(accountHighestBalance.getBalance(), is(new BigDecimal(334455)));
    }

    @Test
    public void givenAccounts_thenObtainAccountFrequentlyUsedSource() {
        Map<Long, Account> accounts = getAccounts();

        Account accountHighestBalance = TransfersApplication.getAccountFrequentlyUsedSource(accounts);

        assertThat(accountHighestBalance.getNumber(), is(112233l));
    }
}
