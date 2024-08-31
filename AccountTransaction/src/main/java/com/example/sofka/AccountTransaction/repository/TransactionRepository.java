package com.example.sofka.AccountTransaction.repository;

import com.example.sofka.AccountTransaction.model.Transaction;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface TransactionRepository extends R2dbcRepository<Transaction, Long> {
    Flux<Transaction> findByAccountIdAndDateBetween(Long accountId, LocalDateTime startDate, LocalDateTime endDate);
}

