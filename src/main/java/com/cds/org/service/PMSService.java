package com.cds.org.service;

import com.cds.org.model.ClientDetails;
import com.cds.org.persistance.PMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PMSService{

    @Autowired
    private PMSRepository repository;

    public void addClient(ClientDetails clientDetail)
    {
        repository.save(clientDetail);

    }

    public Iterable<ClientDetails> getAllClients()
    {
       return repository.findAll();

    }
}
