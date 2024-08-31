package com.example.sofka.AccountTransaction.model.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ReportResponse {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<AccountReportDTO> accountReports;

    // Constructor
    public ReportResponse(LocalDateTime startDate, LocalDateTime endDate, List<AccountReportDTO> accountReports) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.accountReports = accountReports;
    }

    // Getters y Setters
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<AccountReportDTO> getAccountReports() {
        return accountReports;
    }

    public void setAccountReports(List<AccountReportDTO> accountReports) {
        this.accountReports = accountReports;
    }

    // toString (opcional)
    @Override
    public String toString() {
        return "ReportResponse{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", accountReports=" + accountReports +
                '}';
    }
}

