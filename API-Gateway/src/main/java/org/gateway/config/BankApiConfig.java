package org.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "bank.api")
public class BankApiConfig {
    private String url;
    private String host;
    private int port;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getBaseUrl() {
        return "http://localhost:8081";
    }
}
