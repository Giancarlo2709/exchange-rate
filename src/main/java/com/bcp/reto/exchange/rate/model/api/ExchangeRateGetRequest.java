package com.bcp.reto.exchange.rate.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRateGetRequest {

  @Schema(type = "String",
          name = "currencyCodeOrig",
          description = "Origin Currency",
          example = "USD"
  )
  @NotBlank(message = "{message.exchange.rate.currency.orig.required}")
  private String currencyCodeOrig;

  @Schema(type = "String",
          name = "currencyCodeDest",
          description = "Destination Currency",
          example = "PEN"
  )
  @NotBlank(message = "{message.exchange.rate.currency.dest.required}")
  private String currencyCodeDest;

  @Schema(type = "String",
          name = "amount",
          description = "Amount to convert",
          example = "5.0"
  )
  @NotNull(message = "{message.exchange.rate.amount.required}")
  private BigDecimal amount;
}
