package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"org.example", "org.example"})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages="org.example.repository")
@EntityScan(basePackages="org.example.entity")
public class CustomerApiApp {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApiApp.class, args);
    }
}
