package com.bcp.reto.exchange.rate.repository;

import com.bcp.reto.exchange.rate.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExchangeRateRepository  extends JpaRepository<ExchangeRate, Long> {

  ExchangeRate findByCurrencyCodeOrigAndCurrencyCodeDestAndExchangeRateDate(
          String currencyCodeOrig,
          String currencyCodeDest,
          LocalDate exchangeRateDate);

}
