package com.ixaris.interview.transfers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
//            log.info("Preloading " + repository.save(new Account(112233l, BigDecimal.ZERO)));
//            log.info("Preloading " + repository.save(new Account(223344l, BigDecimal.ZERO)));
//            log.info("Preloading " + repository.save(new Account(334455l, BigDecimal.ZERO)));
        };
    }
}
