package com.cds.org.service;

import com.cds.org.exceptions.ClientDetailsNotFoundException;
import com.cds.org.model.ClientDetails;
import com.cds.org.model.ClientDetailsIdentity;
import com.cds.org.persistence.ClientDetailsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientDetailsDAO clientDetailsDAO;

    public ClientDetails addClient(ClientDetails clientDetail) {
       return  this.clientDetailsDAO.saveClientDetails(clientDetail);

    }

    public List<ClientDetails> getAllClients() {
        return this.clientDetailsDAO.getAllClientDetails();
    }

    public ClientDetails getClientByID(ClientDetailsIdentity id) throws ClientDetailsNotFoundException {
       return this.clientDetailsDAO.getClientDetailsById(id);
    }

}

