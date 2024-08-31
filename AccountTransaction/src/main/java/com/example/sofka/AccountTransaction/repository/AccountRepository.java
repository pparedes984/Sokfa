package com.example.sofka.AccountTransaction.repository;

import com.example.sofka.AccountTransaction.model.Account;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends R2dbcRepository<Account, Long> {
    Mono<Account> findByAccountNumber(String accountNumber);
}

