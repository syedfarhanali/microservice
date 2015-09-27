package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class App {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

}
