package com.bcp.reto.exchange.rate.business.impl;

import com.bcp.reto.exchange.rate.adapter.ExchangeRateAdapter;
import com.bcp.reto.exchange.rate.business.ExchangeRateService;
import com.bcp.reto.exchange.rate.config.ErrorMessagesProperties;
import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateGetRequest;
import com.bcp.reto.exchange.rate.model.api.response.ExchangeRateGetResponse;
import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateUpdateRequest;
import com.bcp.reto.exchange.rate.repository.ExchangeHistoryRepository;
import com.bcp.reto.exchange.rate.repository.ExchangeRateRepository;
import io.reactivex.rxjava3.core.Maybe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private final ExchangeRateRepository exchangeRateRepository;
  private final ExchangeHistoryRepository exchangeHistoryRepository;
  private final ErrorMessagesProperties errorMessagesProperties;

  @Autowired
  public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository,
                                 ExchangeHistoryRepository exchangeHistoryRepository,
                                 ErrorMessagesProperties errorMessagesProperties) {
    this.exchangeRateRepository = exchangeRateRepository;
    this.exchangeHistoryRepository = exchangeHistoryRepository;
    this.errorMessagesProperties = errorMessagesProperties;
  }

  @Override
  public Maybe<ExchangeRateGetResponse> exchange(ExchangeRateGetRequest exchangeRateGetRequest) {
    return Maybe.just(exchangeRateRepository.findByCurrencyCodeOrigAndCurrencyCodeDestAndExchangeRateDate(
            exchangeRateGetRequest.getCurrencyCodeOrig(),
            exchangeRateGetRequest.getCurrencyCodeDest(),
            LocalDate.now()))
            .doOnError(e -> {
              log.error(errorMessagesProperties.getExchangeRateErrorServer(), e);
              Maybe.error(new Exception(errorMessagesProperties.getExchangeRateErrorServer(), e));
            })
            .switchIfEmpty(Maybe.error(new Exception(errorMessagesProperties.getExchangeRateNotFound())))
            .map(result -> ExchangeRateAdapter.convertExchangeRateToExchangeRateGetResponse(
                       exchangeHistoryRepository.save(ExchangeRateAdapter.convertExchangeRateToHistory(result,
                          exchangeRateGetRequest)))
            );
  }

  @Override
  public Maybe<ExchangeRateUpdateRequest> updateExchangeRate(ExchangeRateUpdateRequest exchangeRateUpdateRequest) {
    return Maybe.just(exchangeRateRepository.findByCurrencyCodeOrigAndCurrencyCodeDestAndExchangeRateDate(
            exchangeRateUpdateRequest.getCurrencyCodeOrig(),
            exchangeRateUpdateRequest.getCurrencyCodeDest(),
            exchangeRateUpdateRequest.getExchangeRateDate()))
            .doOnError(e -> {
              log.error(errorMessagesProperties.getExchangeRateErrorServer(), e);
              Maybe.error(new Exception(errorMessagesProperties.getExchangeRateErrorServer(), e));
            })
            .switchIfEmpty(Maybe.error(new Exception(errorMessagesProperties.getExchangeRateNotFound())))
            .map(result -> {
              exchangeRateRepository.save(
                      ExchangeRateAdapter.updateExchangeRate(result, exchangeRateUpdateRequest));
              return exchangeRateUpdateRequest;
            });
  }
}
