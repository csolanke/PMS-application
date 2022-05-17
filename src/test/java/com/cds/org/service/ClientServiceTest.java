package com.cds.org.service;

import com.cds.org.model.ClientDetails;
import com.cds.org.persistence.ClientDetailsRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@DataJpaTest
class ClientServiceTest {

    ClientService clientService;

    @Autowired
    ClientDetailsRepository clientDetailsRepository ;

    @Test
    void testAddClient() {

        clientService = new ClientService(clientDetailsRepository);
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1);
        clientDetails.setClientName("chandrakant");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        clientDetails.setClientBrokerAccountName("Zerodha");
        clientDetails.setPaymentMode("Online");
        clientDetails.setClientEmailId("csolanke77@gmail.com");
        clientDetails.setClientAddress("LakeMary Florida");
        clientDetails.setPmsPurchasedDate(LocalDate.of(2021,11,25));

        ClientDetails savedClientDetails = clientDetailsRepository.save(clientDetails);

        assertThat(savedClientDetails.getClientId()).isEqualTo(2);
        assertThat(savedClientDetails).isNotNull();
    }

    @Test
    void testGetAllClients() {

        clientService = new ClientService(clientDetailsRepository);

        clientService = new ClientService(clientDetailsRepository);
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1);
        clientDetails.setClientName("chandrakant");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        clientDetails.setClientBrokerAccountName("Zerodha");
        clientDetails.setPaymentMode("Online");
        clientDetails.setClientEmailId("csolanke77@gmail.com");
        clientDetails.setClientAddress("LakeMary Florida");
        clientDetails.setPmsPurchasedDate(LocalDate.of(2021,11,25));

        ClientDetails savedClientDetails = clientDetailsRepository.save(clientDetails);


        Iterable<ClientDetails> allClients = clientDetailsRepository.findAll();

        assertThat(allClients.iterator().hasNext()).isTrue();
        assertThat(allClients).isNotNull();
        assertThat(allClients).isInstanceOf(ArrayList.class);

    }
}