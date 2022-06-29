package com.cds.org.dto;

public class ClientDetailsIdentityDTO {

    private Long clientId;
    private String clientName;
    private String clientEmailId;

    public ClientDetailsIdentityDTO() {
    }

    public ClientDetailsIdentityDTO(Long clientId, String clientName, String clientEmailId) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmailId = clientEmailId;
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
}
