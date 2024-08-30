package com.example.sofka.AccountTransaction.controller;

import com.example.sofka.AccountTransaction.model.Account;
import com.example.sofka.AccountTransaction.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Flux<Account> getAllAccounts() {
        logger.debug("Handling GET request to /cuentas");
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Account>> getAccountById(@PathVariable Long id) {
        logger.debug("Handling GET request to /cuentas/{}", id);
        return accountService.getAccountById(id)
                .map(account -> ResponseEntity.ok(account))
                .onErrorResume(e -> {
                    logger.error("Error al obtener cuenta con ID: {}", id, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
                });
    }

    @PostMapping
    public Mono<ResponseEntity<Account>> createAccount(@RequestBody Account account) {
        logger.debug("Handling POST request to /cuentas with account: {}", account);
        return accountService.saveAccount(account)
                .map(savedAccount -> ResponseEntity.status(HttpStatus.CREATED).body(savedAccount))
                .onErrorResume(e -> {
                    logger.error("Error al crear cuenta");
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
                });
    }

    /*@PostMapping
    public Mono<ResponseEntity<Account>> createAccount(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("accountType") String accountType,
            @RequestParam("openingBalance") Double openingBalance,
            @RequestParam("state") boolean state,
            @RequestParam("clientId") Long clientId) {

        logger.debug("Handling POST request to /cuentas");
        return accountService.saveAccount(accountNumber, accountType, openingBalance, state, clientId)
                .map(account -> ResponseEntity.status(HttpStatus.CREATED).body(account))
                .onErrorResume(e -> {
                    logger.error("Error al crear cuenta", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
                });
    }*/

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Account>> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        logger.debug("Handling PUT request to /cuentas/{}", id);
        return accountService.updateAccount(id, accountDetails)
                .map(account -> ResponseEntity.ok(account))
                .onErrorResume(e -> {
                    logger.error("Error al actualizar cuenta con ID: {}", id, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
                });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteAccount(@PathVariable Long id) {
        logger.debug("Handling DELETE request to /cuentas/{}", id);
        return accountService.deleteAccount(id)
                .then(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(e -> {
                    logger.error("Error al eliminar cuenta con ID: {}", id, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                });
    }

}
