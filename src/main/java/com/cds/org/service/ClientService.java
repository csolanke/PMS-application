package com.cds.org.service;

import com.cds.org.model.ClientDetails;
import com.cds.org.persistence.ClientDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {


    ClientDetailsRepository repository;

    public ClientService(ClientDetailsRepository repository)
    {
        this.repository = repository;
    }

    public void addClient(ClientDetails clientDetail)
    {
        this.repository.save(clientDetail);

    }

    public Iterable<ClientDetails> getAllClients()
    {
       return this.repository.findAll();

    }
}
