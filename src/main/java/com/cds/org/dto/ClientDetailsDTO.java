package com.cds.org.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ClientDetailsDTO {


    private int clientId;

    @NotBlank (message = "Client name must be provided")
    private String clientName;

    @NotNull
    @Email (message = "Email address is not valid")
    private String clientEmailId;
    private String clientAddress;

    @NotBlank(message = "clients broker name required")
    private String clientBrokerAccountName;

   //@NotNull(message = "date is required")
    private LocalDate pmsPurchasedDate;
    private String paymentMode;

    @NotNull(message = "portfolio amount is required")
    private BigDecimal clientPortfolioAmount;

    public ClientDetailsDTO() {
        super();
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = Math.toIntExact(clientId);
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

    public LocalDate getPmsPurchasedDate() {
        return pmsPurchasedDate;
    }

    public void setPmsPurchasedDate(LocalDate pmsPurchasedDate) {
        this.pmsPurchasedDate = pmsPurchasedDate;
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
