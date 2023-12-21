package com.won.bookappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.won"})
public class BookAppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookAppApiApplication.class, args);
    }

}
