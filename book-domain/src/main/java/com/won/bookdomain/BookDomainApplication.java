package com.won.bookdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookDomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookDomainApplication.class, args);
    }

}
