package com.bcp.reto.exchange.rate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_history")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExchangeHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="exchange_rate_id")
  private ExchangeRate exchangeRate;

  private BigDecimal amount;

  @Column(name = "exchange_amount")
  private BigDecimal exchangeAmount;

  @Column(name = "exchange_history_time")
  private LocalDateTime exchangeHistoryTime;

}
