package com.bcp.reto.exchange.rate.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ExchangeRateAllResponse {

  @Schema(type = "Long",
          name = "id",
          description = "Id",
          example = "1"
  )
  private Long id;

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
          description = "Exchange rate",
          example = "4.15"
  )
  private BigDecimal amount;

  @Schema(type = "String",
          name = "exchangeRateDate",
          description = "exchangeRateDate",
          example = "2021-10-05"
  )
  private LocalDate exchangeRateDate;
}
