package com.cds.org.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClientDetailsIdentity  implements Serializable {

    private Long clientId;
    private String clientName;
    private String clientEmailId;

    public ClientDetailsIdentity(Long clientId, String clientName, String clientEmailId) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmailId = clientEmailId;
    }

    public ClientDetailsIdentity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDetailsIdentity that = (ClientDetailsIdentity) o;
        return clientId.equals(that.clientId) && clientName.equals(that.clientName) && clientEmailId.equals(that.clientEmailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientName, clientEmailId);
    }
}
