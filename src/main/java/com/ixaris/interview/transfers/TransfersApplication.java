package com.ixaris.interview.transfers;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * <p>
 * A Command-line application to parse and process a transfers file and provide the business requirements, namely:
 * 	<ul>
 * 	    <li>1. Print the final balances on all bank accounts</li>
 * 	    <li>2. Print the bank account with the highest balance</li>
 * 	    <li>3. Print the most frequently used source bank account</li>
 * 	</ul>
 * </p>
 */
@SpringBootApplication
@Log4j2
public class TransfersApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TransfersApplication.class, args);
    }

    @Override
    public void run(final String... args) {
        final URL url = getClass().getClassLoader().getResource("transfers.txt");
        try (FileReader fileReader = new FileReader(new File(url.toURI()))) {
            List<Transfer> transfers = getTransfers(fileReader, 3);
            Map<Long, Account> accounts = getAccounts(transfers);

            System.out.println("#Balances");
            for (Map.Entry<Long, Account> entry : accounts.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue().getBalance());
            }

            System.out.println("#Bank Account with highest balance");
            Account accountHighestBalance = getAccountHighestBalance(accounts);
            System.out.println(accountHighestBalance.getNumber());

            System.out.println("#Frequently used source bank account");
            Account accountFrequentlyUsedSource = getAccountFrequentlyUsedSource(accounts);
            System.out.println(accountFrequentlyUsedSource.getNumber());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private List<Transfer> getTransfers(FileReader fileReader, int skipLines) {
        return (List<Transfer>) new CsvToBeanBuilder(fileReader)
                .withSkipLines(skipLines)
                .withType(Transfer.class)
                .build()
                .parse();
    }

    private Account getAccountHighestBalance(Map<Long, Account> accounts) {
        return getAccountHighest(accounts, Comparator.comparing(Account::getBalance));
    }

    private Account getAccountFrequentlyUsedSource(Map<Long, Account> accounts) {
        return getAccountHighest(accounts, Comparator.comparing(Account::getUseAsSourceCount));
    }

    private Account getAccountHighest(Map<Long, Account> accounts, Comparator<Account> comparing) {
        return accounts.values().stream()
                .max(comparing)
                .orElseThrow(NoSuchElementException::new);
    }

    private Map<Long, Account> getAccounts(List<Transfer> transfers) {
        Map<Long, Account> accounts = new HashMap<>();
        for (Transfer transfer : transfers) {
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
        return accounts;
    }
}
