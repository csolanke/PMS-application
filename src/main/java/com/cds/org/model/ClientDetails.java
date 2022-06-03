package com.cds.org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ClientDetails {

    @Id
    @GeneratedValue
    private Long clientId;
    private String clientName;
    private String clientEmailId;
    private String clientAddress;
    private String clientBrokerAccountName;
    private LocalDate pmsPurchasedDate;
    private String paymentMode;
    private BigDecimal clientPortfolioAmount;

    public ClientDetails() {
        //default constructor
    }

    public LocalDate getPmsPurchasedDate() {
        return pmsPurchasedDate;
    }

    public void setPmsPurchasedDate(LocalDate pmsPurchasedDate) {
        this.pmsPurchasedDate = pmsPurchasedDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmailId() {
        return clientEmailId;
    }

    public void setClientEmailId(String clientEmailId) {
        this.clientEmailId = clientEmailId;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientBrokerAccountName() {
        return clientBrokerAccountName;
    }

    public void setClientBrokerAccountName(String clientBrokerAccountName) {
        this.clientBrokerAccountName = clientBrokerAccountName;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public BigDecimal getClientPortfolioAmount() {
        return clientPortfolioAmount;
    }

    public void setClientPortfolioAmount(BigDecimal clientPortfolioAmount) {
        this.clientPortfolioAmount = clientPortfolioAmount;
    }


}
