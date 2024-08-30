package com.example.sofka.AccountTransaction.model.dto;

import java.util.Date;
import java.util.List;

public class ReportResponse {

    private Date startDate;
    private Date endDate;
    private List<AccountReportDTO> accountReports;

    // Constructor
    public ReportResponse(Date startDate, Date endDate, List<AccountReportDTO> accountReports) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.accountReports = accountReports;
    }

    // Getters y Setters
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

