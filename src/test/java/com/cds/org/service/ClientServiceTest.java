

package com.cds.org.service;

import com.cds.org.model.ClientDetails;
import com.cds.org.model.ClientDetailsIdentity;
import com.cds.org.persistence.ClientDetailsDAO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@DataJpaTest
class ClientServiceTest {

    @Mock
  ClientService clientService;

  @Mock
    ClientDetailsDAO clientDetailsDAO;

    @AfterEach
    void tearDown()
    {
      //  clientDetailsDAO.deleteAll();
    }

    @Test
    void testAddClient() {

        clientService = new ClientService();
        ClientDetails clientDetails = new ClientDetails();

        ClientDetailsIdentity Id = new ClientDetailsIdentity();
        Id.setClientId(1L);
        Id.setClientEmailId("csolanke77@gmail.com");
        Id.setClientName("Chandra");

        clientDetails.setId(Id);
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        clientDetails.setClientBrokerAccountName("Zerodha");
        clientDetails.setPaymentMode("Online");
        clientDetails.setClientAddress("LakeMary Florida");
        clientDetails.setPmsPurchasedDate(LocalDate.of(2021,11,25));

        clientDetailsDAO.saveClientDetails(clientDetails);

        assertThat(clientDetails.getId().getClientName()
        ).isEqualTo("Chandra");
        assertThat(clientDetails).isNotNull();
    }

    /*@Test
    void testGetAllClients() {

        clientService = new ClientService(clientDetailsDAO);

        clientService = new ClientService(clientDetailsDAO);
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(1L);
        clientDetails.setClientName("chandrakant");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        clientDetails.setClientBrokerAccountName("Zerodha");
        clientDetails.setPaymentMode("Online");
        clientDetails.setClientEmailId("csolanke77@gmail.com");
        clientDetails.setClientAddress("LakeMary Florida");
        clientDetails.setPmsPurchasedDate(LocalDate.of(2021,11,25));

        ClientDetails savedClientDetails = clientDetailsDAO.save(clientDetails);


        Iterable<ClientDetails> allClients = clientDetailsDAO.findAll();

        assertThat(allClients.iterator().hasNext()).isTrue();
        assertThat(allClients).isNotNull();
        assertThat(allClients).isInstanceOf(ArrayList.class);

    }*/
}

