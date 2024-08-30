package com.example.sofka.AccountTransaction.controller;

import com.example.sofka.AccountTransaction.model.dto.ReportResponse;
import com.example.sofka.AccountTransaction.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public Mono<ReportResponse> getReport(
            @RequestParam Long clientId,
            @RequestParam Date startDate,
            @RequestParam Date endDate) {


        return reportService.generateReport(startDate, endDate);
    }
}

