package com.cds.org.service;

import com.cds.org.exceptions.ResourceNotFoundException;
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

    public ClientDetails getClientByID(Long id) throws ResourceNotFoundException {

        Optional<ClientDetails> clientDetailsOptional = this.repository.findById(id);
        if (!clientDetailsOptional.isPresent()) {
                throw new ResourceNotFoundException("Client with ID : " +id +" does not exist");
        }

        return clientDetailsOptional.get();

    }
}

