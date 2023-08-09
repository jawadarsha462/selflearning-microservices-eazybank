package com.test.accounts.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "accounts")
@Data
@ToString
public class AccountsServiceConfig {
    private String msg;
    private String buildVersion;
}
