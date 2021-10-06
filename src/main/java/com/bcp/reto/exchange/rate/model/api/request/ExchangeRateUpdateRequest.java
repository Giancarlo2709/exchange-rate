package com.bcp.reto.exchange.rate.model.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExchangeRateUpdateRequest {

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
          name = "exchangeRateAmount",
          description = "Amount to convert",
          example = "5.0"
  )
  private BigDecimal exchangeRateAmount;


  private LocalDate exchangeRateDate;
}
