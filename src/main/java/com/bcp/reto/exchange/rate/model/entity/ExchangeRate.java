package com.bcp.reto.exchange.rate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_rate")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExchangeRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "currency_code_orig")
  private String currencyCodeOrig;

  @Column(name = "currency_code_dest")
  private String currencyCodeDest;

  private BigDecimal amount;

  @Column(name = "exchange_rate_date")
  private LocalDate exchangeRateDate;
}
