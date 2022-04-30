package com.cds.org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class PMSDetails {
    @Id
    @GeneratedValue
    private String fundManagerName;
    private BigDecimal totalFundValue;
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
