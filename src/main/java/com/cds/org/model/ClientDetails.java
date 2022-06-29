package com.cds.org.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ClientDetails {

    @EmbeddedId
    private ClientDetailsIdentity id;
    private String clientAddress;
    private String clientBrokerAccountName;
    private LocalDate pmsPurchasedDate;
    private String paymentMode;
    private BigDecimal clientPortfolioAmount;

    public ClientDetails() {
        //default constructor
    }


    public ClientDetails(ClientDetailsIdentity id, String clientAddress, String clientBrokerAccountName, LocalDate pmsPurchasedDate, String paymentMode, BigDecimal clientPortfolioAmount) {
        this.id = id;
        this.clientAddress = clientAddress;
        this.clientBrokerAccountName = clientBrokerAccountName;
        this.pmsPurchasedDate = pmsPurchasedDate;
        this.paymentMode = paymentMode;
        this.clientPortfolioAmount = clientPortfolioAmount;
    }

    public ClientDetailsIdentity getId() {
        return id;
    }

    public void setId(ClientDetailsIdentity id) {
        this.id = id;
    }

    public LocalDate getPmsPurchasedDate() {
        return pmsPurchasedDate;
    }

    public void setPmsPurchasedDate(LocalDate pmsPurchasedDate) {
        this.pmsPurchasedDate = pmsPurchasedDate;
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
