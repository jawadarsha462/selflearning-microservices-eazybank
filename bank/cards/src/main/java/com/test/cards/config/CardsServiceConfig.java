package com.test.cards.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "accounts")
@Data
@ToString
public class CardsServiceConfig {
    private String msg;
    private String buildVersion;
}
