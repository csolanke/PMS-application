package com.cds.org.service;

import com.cds.org.model.ClientDetails;
import com.cds.org.persistence.ClientDetailsRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown()
    {
        clientDetailsRepository.deleteAll();
    }

    @Test
    void testAddClient() {

        clientService = new ClientService(clientDetailsRepository);
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1L);
        clientDetails.setClientName("chandrakant");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        clientDetails.setClientBrokerAccountName("Zerodha");
        clientDetails.setPaymentMode("Online");
        clientDetails.setClientEmailId("csolanke77@gmail.com");
        clientDetails.setClientAddress("LakeMary Florida");
        clientDetails.setPmsPurchasedDate(LocalDate.of(2021,11,25));

        ClientDetails savedClientDetails = clientDetailsRepository.save(clientDetails);

        assertThat(savedClientDetails.getClientName()).isEqualTo("chandrakant");
        assertThat(savedClientDetails).isNotNull();
    }

    @Test
    void testGetAllClients() {

        clientService = new ClientService(clientDetailsRepository);

        clientService = new ClientService(clientDetailsRepository);
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1L);
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