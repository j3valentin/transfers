package com.ixaris.interview.transfers.cli;

import com.ixaris.interview.transfers.domain.Account;
import com.ixaris.interview.transfers.domain.Ixaris;
import com.ixaris.interview.transfers.domain.Transfer;
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
 * @author johard
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
            Ixaris ixaris = new Ixaris(transfers);

            System.out.println("#Balances");
            ixaris.getAccounts().stream().map(Account::toString).forEach(System.out::println);

            System.out.println("#Bank Account with highest balance");
            Account accountHighestBalance = ixaris.getAccountHighestBalance();
            System.out.println(accountHighestBalance.getNumber());

            System.out.println("#Frequently used source bank account");
            Account accountFrequentlyUsedSource = ixaris.getAccountFrequentlyUsedSource();
            System.out.println(accountFrequentlyUsedSource.getNumber());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static List<Transfer> getTransfers(FileReader fileReader, int skipLines) {
        return (List<Transfer>) new CsvToBeanBuilder(fileReader)
                .withSkipLines(skipLines)
                .withType(TransferCSV.class)
                .build()
                .parse();
    }
}
