package com.bcp.reto.exchange.rate.repository;

import com.bcp.reto.exchange.rate.model.entity.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory, Long> {
}
