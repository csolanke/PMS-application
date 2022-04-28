package com.cds.org.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class ClientDetails {

    @Id
    @GeneratedValue
    private int clientId;
    private String clientName;
    private String clientEmailId;
    private String clientAddress;
    private String clientBrokerAccountName;


    private LocalDate pmsPurchasedDate;

    public LocalDate getPmsPurchasedDate() {
        return pmsPurchasedDate;
    }

    public void setPmsPurchasedDate(LocalDate pmsPurchasedDate) {
        this.pmsPurchasedDate = pmsPurchasedDate;
    }

    private String paymentMode;
    private double clientPortfolioAmount;


    public ClientDetails() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
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

    public double getClientPortfolioAmount() {
        return clientPortfolioAmount;
    }

    public void setClientPortfolioAmount(double clientPortfolioAmount) {
        this.clientPortfolioAmount = clientPortfolioAmount;
    }


}
