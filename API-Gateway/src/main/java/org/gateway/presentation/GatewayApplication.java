package org.gateway.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "org/gateway/presentation",
        "org/gateway/application",
        "org/gateway/infrastructure",
        "org/gateway/config"
})
@EnableJpaRepositories(basePackages = "infrastructure.repos")
@EntityScan(basePackages = {
        "infrastructure.entities"
})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}