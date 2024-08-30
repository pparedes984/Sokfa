package com.example.sofka.AccountTransaction.repository;

import com.example.sofka.AccountTransaction.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@Component
public interface AccountRepository extends ReactiveCrudRepository<Account, Long> {
    Flux<Account> findByClientId(Long clientId);
}

