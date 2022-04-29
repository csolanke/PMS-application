package com.cds.org.dto;

public class PMSDetailsDTO {

    private String fundManagerName;
    private double totalFundValue;
    private int numberOfClients;

    public String getFundManagerName() {
        return fundManagerName;
    }

    public void setFundManagerName(String fundManagerName) {
        this.fundManagerName = fundManagerName;
    }

    public double getTotalFundValue() {
        return totalFundValue;
    }

    public void setTotalFundValue(double totalFundValue) {
        this.totalFundValue = totalFundValue;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }
}
