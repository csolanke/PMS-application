package com.cds.org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.reflect.GenericArrayType;
import java.util.Date;

@Entity
public class ClientDetails {

    @Id
    @GeneratedValue
    private int clientId;
    private String clientName;
    private String clientEmailId;
    private String clientAddress;
    private String clientBrokerAccountName;
    private Date pmsPurchasedDate;
    private String paymentMode;
    private double clientPortfolioAmount;

    public ClientDetails() {
    }

    public ClientDetails(int clientId, String clientName, String clientEmailId, String clientAddress, String clientBrokerAccountName, Date pmsPurchasedDate, String paymentMode, double clientPortfolioAmount) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmailId = clientEmailId;
        this.clientAddress = clientAddress;
        this.clientBrokerAccountName = clientBrokerAccountName;
        this.pmsPurchasedDate = pmsPurchasedDate;
        this.paymentMode = paymentMode;
        this.clientPortfolioAmount = clientPortfolioAmount;
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

    public Date getPmsPurchasedDate() {
        return pmsPurchasedDate;
    }

    public void setPmsPurchasedDate(Date pmsPurchasedDate) {
        this.pmsPurchasedDate = pmsPurchasedDate;
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
