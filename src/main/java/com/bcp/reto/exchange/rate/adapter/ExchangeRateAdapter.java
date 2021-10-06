package com.bcp.reto.exchange.rate.adapter;

import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateGetRequest;
import com.bcp.reto.exchange.rate.model.api.response.ExchangeRateGetResponse;
import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateUpdateRequest;
import com.bcp.reto.exchange.rate.model.entity.ExchangeHistory;
import com.bcp.reto.exchange.rate.model.entity.ExchangeRate;

import java.time.LocalDateTime;

public class ExchangeRateAdapter {

  public ExchangeRateAdapter() {
    // method constructor
  }

  public static ExchangeHistory convertExchangeRateToHistory(ExchangeRate exchangeRate,
                                                             ExchangeRateGetRequest request) {
    return ExchangeHistory.builder()
            .exchangeRate(exchangeRate)
            .exchangeHistoryTime(LocalDateTime.now())
            .amount(request.getAmount())
            .exchangeAmount(exchangeRate.getAmount().multiply(request.getAmount()))
            .build();
  }

  public static ExchangeRateGetResponse convertExchangeRateToExchangeRateGetResponse(
          ExchangeHistory history) {
    return ExchangeRateGetResponse.builder()
            .exchangeRateAmount(history.getExchangeRate().getAmount())
            .exchangeAmount(history.getExchangeAmount())
            .amount(history.getAmount())
            .currencyCodeOrig(history.getExchangeRate().getCurrencyCodeOrig())
            .currencyCodeDest(history.getExchangeRate().getCurrencyCodeDest())
            .build();
  }

  public static ExchangeRate updateExchangeRate(ExchangeRate exchangeRate,
                                               ExchangeRateUpdateRequest request) {
    exchangeRate.setAmount(request.getExchangeRateAmount());
    return exchangeRate;
  }
}
