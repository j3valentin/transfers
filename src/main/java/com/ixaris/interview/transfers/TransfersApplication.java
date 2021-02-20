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
import java.util.List;

/**
 * <p>
 * 	A Command-line application to parse and process a transfers file and provide the business requirements, namely:
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
		try (FileReader fileReader = new FileReader(new File(url.toURI())))  {
			List<Transfer> transfers = new CsvToBeanBuilder(fileReader)
					.withSkipLines(3)
					.withType(Transfer.class)
					.build()
					.parse();
			transfers.stream().forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
