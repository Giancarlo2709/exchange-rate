package com.bcp.reto.exchange.rate.business;

import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateGetRequest;
import com.bcp.reto.exchange.rate.model.api.response.ExchangeRateAllResponse;
import com.bcp.reto.exchange.rate.model.api.response.ExchangeRateGetResponse;
import com.bcp.reto.exchange.rate.model.api.request.ExchangeRateUpdateRequest;
import com.bcp.reto.exchange.rate.model.entity.ExchangeRate;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface ExchangeRateService {

  Maybe<ExchangeRateGetResponse> exchange(ExchangeRateGetRequest exchangeRateGetRequest);

  Maybe<ExchangeRateUpdateRequest> updateExchangeRate(ExchangeRateUpdateRequest exchangeRateUpdateRequest);

  Flowable<ExchangeRateAllResponse> findAll();

  Maybe<ExchangeRate> findById(Long id);
}
