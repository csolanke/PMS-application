package com.cds.org.service;

import com.cds.org.model.ClientDetails;
import com.cds.org.persistence.ClientDetailsRepository;
import org.springframework.stereotype.Service;

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

    public Iterable<ClientDetails> getAllClients() {
        return this.repository.findAll();

    }

    public ClientDetails getClientByID(Long id) {
        Optional<ClientDetails> byId = this.repository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            new ResourceNotFoundException("Requested resource does not exist");
        }

        return null;
    }
}
