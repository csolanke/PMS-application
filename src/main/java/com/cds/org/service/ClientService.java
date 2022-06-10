package com.cds.org.service;

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
       return  this.repository.findAll();
    }

    public ClientDetails getClientByID(Long id) {

        Optional<ClientDetails> clientDetailsOptional = this.repository.findById(id);
        if (clientDetailsOptional.isPresent()) {
            return clientDetailsOptional.get();
        } else {
            try {
                throw new ResourceNotFoundException("Requested resource does not exist");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
           return null;
    }
    }

