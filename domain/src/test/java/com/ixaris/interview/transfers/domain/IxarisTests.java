//package com.ixaris.interview.transfers.domain;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//@SpringBootTest
//class IxarisTests {
//
//    @Test
//    void contextLoads() {}
//
//    private Ixaris ixaris() {
//        List<Transfer> transfers = new ArrayList(Arrays.asList(
//                new Transfer(0l, 112233l, new BigDecimal(60.00)),
//                new Transfer(0l, 223344l, new BigDecimal(25.03)),
//                new Transfer(0l, 334455l, new BigDecimal(67.67)),
//                new Transfer(112233l, 223344l, new BigDecimal(11.11)),
//                new Transfer(112233l, 334455l, new BigDecimal(12.12)),
//                new Transfer(223344l, 334455l, new BigDecimal(006.018))
//        ));
//        return new Ixaris(transfers);
//    }
//
//    @Test
//    public void givenTransferList_thenObtainAccounts() {
//        Collection<Account> accounts = new HashMap<Long, Account>() {{
//            put(112233l, new Account(112233l, new BigDecimal(36.77)));
//            put(223344l, new Account(223344l, new BigDecimal(30.12)));
//            put(334455l, new Account(334455l, new BigDecimal(85.81)));
//        }}.values();
//
//        assertThat(ixaris().getAccounts().size(), is(accounts.size()));
//    }
//
//    @Test
//    public void givenAccounts_thenObtainAccountHighestBalance() {
//        Account accountHighestBalance = ixaris().getAccountHighestBalance();
//
//        assertThat(accountHighestBalance.getNumber(), is(334455l));
//    }
//
//    @Test
//    public void givenAccounts_thenObtainAccountFrequentlyUsedSource() {
//        Account accountHighestBalance = ixaris().getAccountFrequentlyUsedSource();
//
//        assertThat(accountHighestBalance.getNumber(), is(112233l));
//    }
//}
