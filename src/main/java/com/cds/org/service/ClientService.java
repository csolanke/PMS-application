package com.cds.org.service;

import com.cds.org.exceptions.ClientDetailsNotFoundException;
import com.cds.org.model.ClientDetails;
import com.cds.org.persistence.ClientDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {


    ClientDetailsRepository repository;

    public ClientService(ClientDetailsRepository repository) {
        this.repository = repository;
    }

    public void addClient(ClientDetails clientDetail) {
        this.repository.save(clientDetail);

    }

    public List<ClientDetails> getAllClients() {
        return this.repository.findAll();
    }

    public ClientDetails getClientByID(Long id) throws ClientDetailsNotFoundException {

        Optional<ClientDetails> clientDetailsOptional = this.repository.findById(id);
        if (!clientDetailsOptional.isPresent()) {
                throw new ClientDetailsNotFoundException("client details not found with Id : "+id);
        }

        return clientDetailsOptional.get();

    }
}

