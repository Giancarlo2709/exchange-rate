package com.bcp.reto.exchange.rate.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "exchange-rate.error")
@Getter
@Setter
public class ErrorMessagesProperties {

  private String exchangeRateErrorServer;
  private String exchangeRateNotFound;
}
