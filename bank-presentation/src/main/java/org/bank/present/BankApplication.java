package org.bank.present;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "org.bank.present",
        "org.bank.core",
        "org.bank.memory"
})
@EnableJpaRepositories(basePackages = "org.bank.memory.repos")
@EntityScan(basePackages = {
        "org.bank.memory.entities.accounts",
        "org.bank.memory.entities.users",
        "org.bank.memory.entities.transactions"
})
public class BankApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }
}
