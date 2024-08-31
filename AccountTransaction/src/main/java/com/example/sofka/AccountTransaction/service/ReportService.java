package com.example.sofka.AccountTransaction.service;

import com.example.sofka.AccountTransaction.model.dto.AccountReportDTO;
import com.example.sofka.AccountTransaction.model.dto.ReportResponse;
import com.example.sofka.AccountTransaction.repository.AccountRepository;
import com.example.sofka.AccountTransaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ReportService {

    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;

    public ReportService (AccountRepository accountRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Mono<ReportResponse> generateReport(String startDate, String endDate) {
        return accountRepository.findAll() // Obtén todas las cuentas

                .flatMap(account -> transactionRepository.findByAccountIdAndDateBetween(account.getId(), LocalDateTime.parse(startDate), LocalDateTime.parse(endDate))
                        .collectList()
                        .map(transactions -> new AccountReportDTO(account, transactions))) // Crea DTOs para cada cuenta y sus transacciones
                .collectList() // Agrupa todos los AccountReportDTO en una lista
                .map(accountReports -> new ReportResponse(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate), accountReports)); // Crea el ReportResponse con la lista de AccountReportDTOs y fechas
    }
}






