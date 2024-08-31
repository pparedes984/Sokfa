package com.example.sofka.AccountTransaction.service;

import com.example.sofka.AccountTransaction.exception.ResourceNotFoundException;
import com.example.sofka.AccountTransaction.model.Account;
import com.example.sofka.AccountTransaction.repository.AccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class AccountService {

    private static final Logger logger = LogManager.getLogger(AccountService.class);

    private AccountRepository accountRepository;

    @Autowired
    private ClientServiceClient clientServiceClient;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Flux<Account> getAllAccounts() {
        logger.info("Obteniendo todas las cuentas");
        return accountRepository.findAll();
    }

    public Mono<Account> getAccountById(Long id) {
        logger.info("Obteniendo cuenta con ID: {}", id);
        return accountRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cuenta no encontrada")));
    }

    public Mono<Account> saveAccount(Account account) {
        logger.info("Creando cuenta: {}", account.getAccountNumber());
        return clientServiceClient.verifyClientExists(account.getClientId())
                .flatMap(client -> accountRepository.save(account))
                .doOnError(ex -> {
                    // Loguea el error para un mejor diagnóstico
                    logger.error("Error al comunicarse con client-service", ex);
                    System.err.println("Error creating account: " + ex.getMessage());
                });
    }

    public Mono<Account> updateAccount(Long id, Account accountDetails) {
        logger.info("Actualizando cuenta con ID: {}", id);
        return accountRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cuenta no encontrada")))
                .flatMap(account -> {
                    if (accountDetails.getAccountNumber() != null)
                        account.setAccountNumber(accountDetails.getAccountNumber());
                    if (accountDetails.getAccountType() != null)
                        account.setAccountType(accountDetails.getAccountType());
                    if (accountDetails.getOpeningBalance() != null)
                        account.setOpeningBalance(accountDetails.getOpeningBalance());
                    if (accountDetails.getState() != null)
                        account.setState(accountDetails.getState());
                    return accountRepository.save(account);
                });
    }

    public Mono<Void> deleteAccount(Long id) {
        logger.info("Eliminando cuenta con ID: {}", id);
        return accountRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cuenta no encontrada")))
                .flatMap(account -> accountRepository.delete(account))
                .onErrorResume(e -> {
                    logger.error("Error al eliminar cuenta con ID: {}", id, e);
                    return Mono.error(e);
                });
    }
}
