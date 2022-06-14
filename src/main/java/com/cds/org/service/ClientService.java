package com.cds.org.service;

import com.cds.org.model.ClientDetails;
import com.cds.org.persistence.ClientDetailsDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    ClientDetailsDAO clientDetailsDAO;

    public ClientService( ClientDetailsDAO clientDetailsDAO) {
        this.clientDetailsDAO = clientDetailsDAO;
    }

    public void addClient(ClientDetails clientDetail) {
        this.clientDetailsDAO.saveClientDetails(clientDetail);

    }

    public List<ClientDetails> getAllClients() {
        return this.clientDetailsDAO.getAllClientDetails();
    }

    public ClientDetails getClientByID(Long id)  {
       return this.clientDetailsDAO.getClientDetailsById(id);
    }

}

