package com.example.sofka.AccountTransaction.repository;

import com.example.sofka.AccountTransaction.model.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Date;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {
    Flux<Transaction> findByAccountIdAndDateBetween(Long accountId, Date startDate, Date endDate);
}

