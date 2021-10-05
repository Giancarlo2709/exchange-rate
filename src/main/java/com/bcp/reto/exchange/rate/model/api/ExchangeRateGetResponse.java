package com.bcp.reto.exchange.rate.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ExchangeRateGetResponse {

  @Schema(type = "String",
          name = "currencyCodeOrig",
          description = "Origin Currency",
          example = "USD"
  )
  private String currencyCodeOrig;

  @Schema(type = "String",
          name = "currencyCodeDest",
          description = "Destination Currency",
          example = "PEN"
  )
  private String currencyCodeDest;

  @Schema(type = "String",
          name = "amount",
          description = "Amount to convert",
          example = "20.0"
  )
  private BigDecimal amount;

  @Schema(type = "String",
          name = "exchangeAmount",
          description = "Exchange Amount",
          example = "4.10"
  )
  private BigDecimal exchangeAmount;

  @Schema(type = "String",
          name = "exchangeRateAmount",
          description = "Exchange Rate Amount",
          example = "82.4"
  )
  private BigDecimal exchangeRateAmount;
}
