package com.ixaris.interview.transfers.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ixaris.interview")
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);

//        TransferWebClient transferWebClient = new TransferWebClient();
//        System.out.println(transferWebClient.getResult());
    }
}
