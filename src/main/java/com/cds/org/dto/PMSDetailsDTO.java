package com.cds.org.dto;

import java.math.BigDecimal;

public class PMSDetailsDTO {

    private String fundManagerName;
    private BigDecimal totalFundValue;
    private int numberOfClients;

    public String getFundManagerName() {
        return fundManagerName;
    }

    public void setFundManagerName(String fundManagerName) {
        this.fundManagerName = fundManagerName;
    }

    public BigDecimal getTotalFundValue() {
        return totalFundValue;
    }

    public void setTotalFundValue(BigDecimal totalFundValue) {
        this.totalFundValue = totalFundValue;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }
}
