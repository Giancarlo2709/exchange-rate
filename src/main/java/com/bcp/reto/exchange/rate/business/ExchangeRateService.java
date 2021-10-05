package com.bcp.reto.exchange.rate.business;

import com.bcp.reto.exchange.rate.model.api.ExchangeRateGetRequest;
import com.bcp.reto.exchange.rate.model.api.ExchangeRateGetResponse;
import com.bcp.reto.exchange.rate.model.api.ExchangeRateUpdateRequest;
import io.reactivex.rxjava3.core.Maybe;

public interface ExchangeRateService {

  Maybe<ExchangeRateGetResponse> exchange(ExchangeRateGetRequest exchangeRateGetRequest);

  Maybe<ExchangeRateUpdateRequest> updateExchangeRate(ExchangeRateUpdateRequest exchangeRateUpdateRequest);
}
