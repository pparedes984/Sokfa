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

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionService.class);

    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

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
                    transaction.setDate(LocalDateTime.now());
                    if(transaction.getTransactionType().equals("CREDITO")) {
                        transaction.setBalance(account.getOpeningBalance() + (transaction.getValue()));
                    }
                    else {
                        transaction.setBalance(account.getOpeningBalance() - (transaction.getValue()));
                    }
                    transaction.setAccount(account.getId());

                    if (account.getOpeningBalance() < transaction.getValue()) {
                        logger.error("Saldo no disponible");
                        return Mono.error(new SaldoInsuficienteException("Saldo no disponible"));
                    }
                    account.setOpeningBalance(transaction.getBalance());
                    return accountService.updateAccount(accountId, account)
                            .then(transactionRepository.save(transaction));
                });
    }

    public Mono<Transaction> updateTransaction(Long id, Transaction transactionDetails) {
        logger.info("Actualizando movimiento con ID: {}", id);
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Movimiento no encontrado")))
                .flatMap(transaction -> {
                    if (transactionDetails.getDate() != null)
                        transaction.setDate(transactionDetails.getDate());
                    if (transactionDetails.getTransactionType() != null)
                        transaction.setTransactionType(transactionDetails.getTransactionType());
                    if (transactionDetails.getBalance() != null)
                        transaction.setBalance(transactionDetails.getBalance());
                    if (transactionDetails.getValue() != null)
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
