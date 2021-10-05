package com.bcp.reto.exchange.rate.controller;

import com.bcp.reto.exchange.rate.business.ExchangeRateService;
import com.bcp.reto.exchange.rate.model.api.ExchangeRateGetRequest;
import com.bcp.reto.exchange.rate.model.api.ExchangeRateGetResponse;
import com.bcp.reto.exchange.rate.model.api.ExchangeRateUpdateRequest;
import io.reactivex.rxjava3.core.Maybe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ExchangeRateControllerTest {

  @InjectMocks
  ExchangeRateController exchangeRateController;

  @Mock
  private ExchangeRateService exchangeRateService;

  private ExchangeRateGetRequest exchangeRateGetRequest;
  private ExchangeRateUpdateRequest exchangeRateUpdateRequest;
  private ExchangeRateGetResponse exchangeRateGetResponse;

  @BeforeEach
  void setup() {
    exchangeRateGetRequest = new ExchangeRateGetRequest();
    exchangeRateGetRequest.setAmount(BigDecimal.valueOf(5.4));
    exchangeRateGetRequest.setCurrencyCodeOrig("USD");
    exchangeRateGetRequest.setCurrencyCodeDest("PEN");

    exchangeRateUpdateRequest = new ExchangeRateUpdateRequest();
    exchangeRateUpdateRequest.setCurrencyCodeOrig("USD");
    exchangeRateUpdateRequest.setCurrencyCodeDest("PEN");
    exchangeRateUpdateRequest.setExchangeRateDate(LocalDate.now());
    exchangeRateUpdateRequest.setExchangeRateAmount(BigDecimal.valueOf(4.15));

    exchangeRateGetResponse = ExchangeRateGetResponse.builder()
        .currencyCodeOrig("USD")
        .currencyCodeDest("PEN")
        .amount(BigDecimal.valueOf(4.12))
        .exchangeAmount(BigDecimal.valueOf(22.248))
        .exchangeRateAmount(BigDecimal.valueOf(4.12))
        .build();

  }

  @Test
  void exchangeTest() {
    when(exchangeRateService.exchange(any()))
        .thenReturn(Maybe.just(exchangeRateGetResponse));

    exchangeRateController.exchange(exchangeRateGetRequest)
        .test()
        .assertValue(t -> t.getAmount().equals(BigDecimal.valueOf(4.12)))
        .assertValue(t -> t.getExchangeAmount().equals(BigDecimal.valueOf(22.248)))
        .assertValue(t -> t.getExchangeRateAmount().equals(BigDecimal.valueOf(4.12)))
        .assertValue(t -> t.getCurrencyCodeOrig().equals("USD"))
        .assertValue(t -> t.getCurrencyCodeDest().equals("PEN"));
  }

  @Test
  void updateExchangeTest() {
    when(exchangeRateService.updateExchangeRate(any()))
        .thenReturn(Maybe.just(exchangeRateUpdateRequest));

    exchangeRateController.updateExchange(exchangeRateUpdateRequest)
        .test()
        .assertValue(t -> t.getExchangeRateAmount().equals(BigDecimal.valueOf(4.15)));
  }
}
