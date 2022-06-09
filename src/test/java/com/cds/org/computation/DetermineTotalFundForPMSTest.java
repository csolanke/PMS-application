package com.cds.org.computation;



import com.cds.org.model.ClientDetails;
import com.cds.org.persistence.ClientDetailsRepository;
import com.cds.org.service.ClientService;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mock.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;


class DetermineTotalFundForPMSTest {

    private DetermineTotalFundForPMS determineTotalFundForPMS;
    @Mock
    private ClientService clientService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp()
    {
        autoCloseable = MockitoAnnotations.openMocks(this);
        determineTotalFundForPMS = new DetermineTotalFundForPMS(clientService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }


    @Test
    void testCalculateTotalFundAmount() {

        ClientDetails clientDetails1 = new ClientDetails();
        clientDetails1.setClientId(1l);
        clientDetails1.setClientName("Chandra");
        clientDetails1.setClientEmailId("csolanke77@gmail.com");
        clientDetails1.setClientAddress("LakeMary");
        clientDetails1.setPmsPurchasedDate(LocalDate.now());
        clientDetails1.setClientBrokerAccountName("zerodha");
        clientDetails1.setClientPortfolioAmount(BigDecimal.valueOf(700000));

        ClientDetails clientDetails2 = new ClientDetails();
        clientDetails2.setClientId(2l);
        clientDetails2.setClientName("Amey");
        clientDetails2.setClientEmailId("amey@gmail.com");
        clientDetails2.setClientAddress("LakeMary");
        clientDetails2.setPmsPurchasedDate(LocalDate.now());
        clientDetails2.setClientBrokerAccountName("upstox");
        clientDetails2.setClientPortfolioAmount(BigDecimal.valueOf(800000));


        ArrayList<ClientDetails> allClients = new ArrayList<>();
        allClients.add(clientDetails1);
        allClients.add(clientDetails2);
        Iterable<ClientDetails> allClientsIterable = allClients;

        Mockito.when(clientService.getAllClients()).thenReturn(allClients);


        assertThat(allClients.stream().map(cl->cl.getClientPortfolioAmount())
                .reduce(BigDecimal.ZERO,BigDecimal::add)).
                isEqualByComparingTo(BigDecimal.valueOf(1500000));

        assertThat(determineTotalFundForPMS.calculateTotalFundAmount()).
               isEqualByComparingTo(BigDecimal.valueOf(1500000));

           verify(clientService).getAllClients();


    }


    @Test
    void testCalculateTotalFundAmountIsZero() {

        ClientDetails clientDetails1 = new ClientDetails();
        clientDetails1.setClientId(1l);
        clientDetails1.setClientName("Chandra");
        clientDetails1.setClientEmailId("csolanke77@gmail.com");
        clientDetails1.setClientAddress("LakeMary");
        clientDetails1.setPmsPurchasedDate(LocalDate.now());
        clientDetails1.setClientBrokerAccountName("zerodha");
        clientDetails1.setClientPortfolioAmount(BigDecimal.valueOf(0));

        ClientDetails clientDetails2 = new ClientDetails();
        clientDetails2.setClientId(2l);
        clientDetails2.setClientName("Amey");
        clientDetails2.setClientEmailId("amey@gmail.com");
        clientDetails2.setClientAddress("LakeMary");
        clientDetails2.setPmsPurchasedDate(LocalDate.now());
        clientDetails2.setClientBrokerAccountName("upstox");
        clientDetails2.setClientPortfolioAmount(BigDecimal.valueOf(0));


        ArrayList<ClientDetails> allClients = new ArrayList<>();
        allClients.add(clientDetails1);
        allClients.add(clientDetails2);
        Iterable<ClientDetails> allClientsIterable = allClients;

        Mockito.when(clientService.getAllClients()).thenReturn(allClients);


        assertThat(allClients.stream().map(cl->cl.getClientPortfolioAmount())
                .reduce(BigDecimal.ZERO,BigDecimal::add)).
                isEqualByComparingTo(BigDecimal.valueOf(0));

        assertThat(determineTotalFundForPMS.calculateTotalFundAmount()).
                isEqualByComparingTo(BigDecimal.valueOf(0));

        verify(clientService).getAllClients();


    }
}