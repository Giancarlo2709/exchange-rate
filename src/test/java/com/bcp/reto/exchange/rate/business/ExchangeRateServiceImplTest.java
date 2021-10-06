package com.bcp.reto.exchange.rate.business;

import com.bcp.reto.exchange.rate.business.impl.ExchangeRateServiceImpl;
import com.bcp.reto.exchange.rate.config.ErrorMessagesProperties;
import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateGetRequest;
import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateUpdateRequest;
import com.bcp.reto.exchange.rate.model.entity.ExchangeHistory;
import com.bcp.reto.exchange.rate.model.entity.ExchangeRate;
import com.bcp.reto.exchange.rate.repository.ExchangeHistoryRepository;
import com.bcp.reto.exchange.rate.repository.ExchangeRateRepository;
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

@ExtendWith({SpringExtension.class})
public class ExchangeRateServiceImplTest {

  @InjectMocks
  ExchangeRateServiceImpl exchangeRateService;

  @Mock
  private ExchangeRateRepository exchangeRateRepository;

  @Mock
  private ExchangeHistoryRepository exchangeHistoryRepository;

  @Mock
  private ErrorMessagesProperties errorMessagesProperties;

  private ExchangeRate exchangeRate;
  private ExchangeHistory exchangeHistory;
  private ExchangeRateGetRequest exchangeRateGetRequest;
  private ExchangeRateUpdateRequest exchangeRateUpdateRequest;

  @BeforeEach
  void setup() {
    exchangeRate = ExchangeRate.builder()
        .id(1L)
        .amount(BigDecimal.valueOf(4.12))
        .currencyCodeOrig("USD")
        .currencyCodeDest("PEN")
        .exchangeRateDate(LocalDate.now())
        .build();

    exchangeHistory = ExchangeHistory.builder()
        .id(1L)
        .amount(BigDecimal.valueOf(5.4))
        .exchangeRate(exchangeRate)
        .exchangeAmount(BigDecimal.valueOf(22.248))
        .build();

    exchangeRateGetRequest = new ExchangeRateGetRequest();
    exchangeRateGetRequest.setAmount(BigDecimal.valueOf(5.4));
    exchangeRateGetRequest.setCurrencyCodeOrig("USD");
    exchangeRateGetRequest.setCurrencyCodeDest("PEN");

    exchangeRateUpdateRequest = new ExchangeRateUpdateRequest();
    exchangeRateUpdateRequest.setCurrencyCodeOrig("USD");
    exchangeRateUpdateRequest.setCurrencyCodeDest("PEN");
    exchangeRateUpdateRequest.setExchangeRateDate(LocalDate.now());
    exchangeRateUpdateRequest.setExchangeRateAmount(BigDecimal.valueOf(4.15));

    errorMessagesProperties.setExchangeRateNotFound("Tipo de cambio no encontrado");
    errorMessagesProperties.setExchangeRateErrorServer("Ocurrio un error en el servidor");

  }

  @Test
  void exchangeTest() {
    when(exchangeRateRepository.findByCurrencyCodeOrigAndCurrencyCodeDestAndExchangeRateDate(any(), any(), any()))
        .thenReturn(exchangeRate);

    when(exchangeHistoryRepository.save(any()))
        .thenReturn(exchangeHistory);

    exchangeRateService.exchange(exchangeRateGetRequest)
        .test()
        .assertValue(t -> t.getExchangeRateAmount().equals(BigDecimal.valueOf(4.12)))
        .assertValue(t -> t.getExchangeAmount().equals(BigDecimal.valueOf(22.248)));
  }

  @Test
  void updateExchangeRateTest() {
    exchangeRate.setAmount(BigDecimal.valueOf(4.15));
    when(exchangeRateRepository.findByCurrencyCodeOrigAndCurrencyCodeDestAndExchangeRateDate(any(), any(), any()))
        .thenReturn(exchangeRate);

    when(exchangeRateRepository.save(any()))
        .thenReturn(exchangeRate);

    exchangeRateService.updateExchangeRate(exchangeRateUpdateRequest)
        .test()
        .assertValue(t -> t.getExchangeRateAmount().equals(BigDecimal.valueOf(4.15)));
  }
}
