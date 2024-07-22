package com.banbanmoomani.memilog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MeMiLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeMiLogApplication.class, args);
    }

}
