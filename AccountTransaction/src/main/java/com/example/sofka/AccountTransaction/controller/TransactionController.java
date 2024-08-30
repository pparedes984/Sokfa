package com.example.sofka.AccountTransaction.controller;

import com.example.sofka.AccountTransaction.model.Transaction;
import com.example.sofka.AccountTransaction.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {

    private static final Logger logger = LogManager.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Flux<Transaction> getAllTransactions() {
        logger.debug("Handling GET request to /movimientos");
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Transaction>> getTransactionById(@PathVariable Long id) {
        logger.debug("Handling GET request to /movimientos/{}", id);
        return transactionService.getTransactionById(id)
                .map(transaction -> ResponseEntity.ok(transaction))
                .onErrorResume(e -> {
                    logger.error("Error al obtener movimiento con ID: {}", id, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                });
    }

    @PostMapping("/{accountId}")
    public Mono<ResponseEntity<Transaction>> createTransaction(
            @PathVariable Long accountId, @RequestBody Transaction transaction) {
        logger.debug("Handling POST request to /movimientos");
        return transactionService.saveTransaction(accountId, transaction)
                .map(newTransaction -> ResponseEntity.status(HttpStatus.CREATED).body(newTransaction))
                .onErrorResume(e -> {
                    logger.error("Error al crear movimiento para cuenta con ID: {}", accountId, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Transaction>> updateTransaction(
            @PathVariable Long id, @RequestBody Transaction transactionDetails) {
        logger.debug("Handling PUT request to /movimientos/{}", id);
        return transactionService.updateTransaction(id, transactionDetails)
                .map(updatedTransaction -> ResponseEntity.ok(updatedTransaction))
                .onErrorResume(e -> {
                    logger.error("Error al actualizar movimiento con ID: {}", id, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteTransaction(@PathVariable Long id) {
        logger.debug("Handling DELETE request to /movimientos/{}", id);
        return transactionService.deleteTransaction(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(e -> {
                    logger.error("Error al eliminar movimiento con ID: {}", id, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                });
    }
}
