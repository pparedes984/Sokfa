package com.example.sofka.AccountTransaction.service;

import com.example.sofka.AccountTransaction.model.dto.AccountReportDTO;
import com.example.sofka.AccountTransaction.model.dto.ReportResponse;
import com.example.sofka.AccountTransaction.repository.AccountRepository;
import com.example.sofka.AccountTransaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ReportService {

    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;

    public ReportService (AccountRepository accountRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Mono<ReportResponse> generateReport(Date startDate, Date endDate) {
        return accountRepository.findAll() // Obtén todas las cuentas
                .flatMap(account -> transactionRepository.findByAccountIdAndDateBetween(account.getId(), startDate, endDate)
                        .collectList()
                        .map(transactions -> new AccountReportDTO(account, transactions))) // Crea DTOs para cada cuenta y sus transacciones
                .collectList() // Agrupa todos los AccountReportDTO en una lista
                .map(accountReports -> new ReportResponse(startDate, endDate, accountReports)); // Crea el ReportResponse con la lista de AccountReportDTOs y fechas
    }
}

    /*public Mono<ReportResponse> generateReport(Long clientId, Date startDate, Date endDate) {
        // Obtener todas las cuentas del cliente
        return accountRepository.findByClientId(clientId)
                .collectList()
                .flatMap(accounts -> {
                    // Obtener transacciones para todas las cuentas en un flujo único
                    return Flux.fromIterable(accounts)
                            .flatMap(account ->
                                    transactionRepository.findByAccountIdAndDateBetween(account.getId(), startDate, endDate)
                                            .collectList()
                                            .map(transactions -> new ReportResponse(account, transactions))
                            )
                            .collectList() // Recolectar todos los ReportResponse generados en una lista
                            .map(reportResponses -> {
                                List<Account> accountList = accounts;
                                List<Transaction> allTransactions = reportResponses.stream()
                                        .flatMap(report -> report.getTransactions().stream())
                                        .toList();
                                return new ReportResponse(accountList, allTransactions);
                            });
                })
                .onErrorResume(e -> Mono.error(new RuntimeException("Error generating report", e)));
    }*/





