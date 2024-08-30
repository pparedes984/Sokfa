package com.example.sofka.AccountTransaction.service;

import com.example.sofka.AccountTransaction.exception.ResourceNotFoundException;
import com.example.sofka.AccountTransaction.exception.SaldoInsuficienteException;
import com.example.sofka.AccountTransaction.model.Transaction;
import com.example.sofka.AccountTransaction.repository.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Date;

@Service
public class TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionService.class);

    @Autowired(required = false)
    private TransactionRepository transactionRepository;

    @Autowired(required = false)
    private AccountService accountService;

    public Flux<Transaction> getAllTransactions() {
        logger.info("Obteniendo todos los movimientos");
        return transactionRepository.findAll();
    }

    public Mono<Transaction> getTransactionById(Long id) {
        logger.info("Obteniendo movimiento con ID: {}", id);
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Movimiento no encontrado")));
    }

    public Mono<Transaction> saveTransaction(Long accountId, Transaction transaction) {
        logger.info("Creando movimiento asociado al numero de cuenta {}", accountId);
        return accountService.getAccountById(accountId)
                .flatMap(account -> {
                    transaction.setDate(new Date());
                    transaction.setBalance(account.getOpeningBalance() + (transaction.getBalance()));
                    transaction.setAccount(account);

                    if (transaction.getBalance() < 0) {
                        logger.error("Saldo no disponible");
                        return Mono.error(new SaldoInsuficienteException("Saldo no disponible"));
                    }

                    transaction.setBalance(transaction.getBalance());
                    return accountService.updateAccount(accountId, account)
                            .then(transactionRepository.save(transaction));
                });
    }

    public Mono<Transaction> updateTransaction(Long id, Transaction transactionDetails) {
        logger.info("Actualizando movimiento con ID: {}", id);
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Movimiento no encontrado")))
                .flatMap(transaction -> {
                    transaction.setDate(transactionDetails.getDate());
                    transaction.setTransactionType(transactionDetails.getTransactionType());
                    transaction.setBalance(transactionDetails.getBalance());
                    transaction.setValue(transactionDetails.getValue());

                    return transactionRepository.save(transaction);
                });
    }

    public Mono<Void> deleteTransaction(Long id) {
        logger.info("Eliminando movimiento con ID: {}", id);
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Movimiento no encontrado")))
                .flatMap(transaction -> transactionRepository.delete(transaction));
    }
}
