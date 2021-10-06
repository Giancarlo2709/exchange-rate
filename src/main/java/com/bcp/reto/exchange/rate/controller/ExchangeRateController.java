package com.bcp.reto.exchange.rate.controller;

import com.bcp.reto.exchange.rate.business.ExchangeRateService;
import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateGetRequest;
import com.bcp.reto.exchange.rate.model.api.response.ExchangeRateGetResponse;
import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateUpdateRequest;
import io.reactivex.rxjava3.core.Maybe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exchange/v1")
@Schema(description = "ExchangeRate", name = "ExchangeRate")
public class ExchangeRateController {

  private final ExchangeRateService exchangeRateService;

  @Autowired
  public ExchangeRateController(ExchangeRateService exchangeRateService) {
    this.exchangeRateService = exchangeRateService;
  }

  @PostMapping(value = "/exchange", produces = { MediaType.APPLICATION_JSON_VALUE })
  @Operation(summary = "Obtiene el tipo de cambio",
    method = "POST",
    description = "Obtiene el tipo de cambio",
    tags = {"ExchangeRate"})
  @ApiResponse(responseCode = "200",
    description = "Tipo de Cambio",
  content = {
          @Content(schema = @Schema(implementation = ExchangeRateGetResponse.class))
  })
  @ApiResponse(responseCode = "500",
          description = "ERROR",
          content = {
                  @Content(schema = @Schema(implementation = Exception.class))
          })
  public Maybe<ExchangeRateGetResponse> exchange(@RequestBody @Valid ExchangeRateGetRequest exchangeRateGetRequest) {
    return this.exchangeRateService.exchange(exchangeRateGetRequest);
  }

  @PutMapping(value = "/exchange", produces = {  MediaType.APPLICATION_JSON_VALUE})
  @Operation(summary = "Actualiza el tipo de cambio",
          method = "PUT",
          description = "Actualiza el tipo de cambio",
          tags = {"ExchangeRate"})
  @ApiResponse(responseCode = "200",
          description = "Tipo de Cambio actualizado",
          content = {
                  @Content(schema = @Schema(implementation = ExchangeRateUpdateRequest.class))
          })
  @ApiResponse(responseCode = "500",
          description = "ERROR",
          content = {
                  @Content(schema = @Schema(implementation = Exception.class))
          })
  public Maybe<ExchangeRateUpdateRequest> updateExchange(@RequestBody ExchangeRateUpdateRequest exchangeRateUpdateRequest)  {
    return this.exchangeRateService.updateExchangeRate(exchangeRateUpdateRequest);
  }
}
