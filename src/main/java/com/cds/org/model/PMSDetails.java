package com.cds.org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PMSDetails {
    @Id
    @GeneratedValue
    private String fundManagerName;
    private double totalFundValue;
    private int numberOfClients;

    public PMSDetails() {
        //default constructor
    }

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
