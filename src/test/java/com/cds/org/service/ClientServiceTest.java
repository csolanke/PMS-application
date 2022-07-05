
package com.cds.org.service;

import com.cds.org.exceptions.ClientDetailsNotFoundException;
import com.cds.org.model.ClientDetails;
import com.cds.org.model.ClientDetailsIdentity;
import com.cds.org.persistence.ClientDetailsDAO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
 public class ClientServiceTest {


    @Mock
    private ClientDetailsDAO clientDetailsDAO;

    private ClientService clientService;
    private ClientDetailsIdentity id;
    private ClientDetails clientDetails;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        clientService = new ClientService(this.clientDetailsDAO);

        id = new ClientDetailsIdentity();
        id.setClientId(1l);
        id.setClientName("chandra");
        id.setClientEmailId("csolanke77@gmail.com");

        clientDetails = new ClientDetails();
        clientDetails.setId(id);
        clientDetails.setClientBrokerAccountName("ZeroBrane");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        clientDetails.setClientAddress("LakeMary");
        //  clientDetails.setPmsPurchasedDate(LocalDate.of(2022,06,14));
        clientDetails.setPaymentMode("Online");
    }

   @Test
    public void testAddClient() {

      Mockito.when(clientService.addClient(Mockito.any())).thenReturn(clientDetails);

       ClientDetails clientDetails = clientService.addClient(Mockito.any());

       Mockito.verify(clientDetailsDAO,Mockito.times(1)).saveClientDetails(Mockito.any());
       Assertions.assertThat(clientDetails.getClientAddress()).isEqualTo("LakeMary");
       Assertions.assertThat(clientDetails).isNotNull();
    }

    @Test
   public  void testGetAllClients() {

        Mockito.when(clientService.getAllClients()).thenReturn(Arrays.asList(clientDetails));

        List<ClientDetails> allClients = clientService.getAllClients();

        Mockito.verify(clientDetailsDAO,Mockito.times(1)).getAllClientDetails();
        Assertions.assertThat(allClients).hasSize(1).isNotNull();
    }

    @Test
    public void testGetClientByID() throws ClientDetailsNotFoundException {

        Mockito.when(clientService.getClientByID(id)).thenReturn(clientDetails);

        ClientDetails ClientDetailsById = clientService.getClientByID(id);

        Mockito.verify(clientDetailsDAO,Mockito.times(1)).getClientDetailsById(Mockito.any());
        Assertions.assertThat(ClientDetailsById.getClientAddress()).isEqualTo("LakeMary");
        Assertions.assertThat(ClientDetailsById.getPaymentMode()).isEqualTo("Online");
        Assertions.assertThat(clientDetails).isNotNull();

    }

    @Test(expected = ClientDetailsNotFoundException.class)
    public void testGetClientByIdFailedCase() throws ClientDetailsNotFoundException {

        ClientDetailsIdentity testId = new ClientDetailsIdentity();
        testId.setClientName("abc");
        testId.setClientEmailId("abc@gmail.com");

        Mockito.when(clientService.getClientByID(testId))
                .thenThrow(new ClientDetailsNotFoundException("ClientDetails with this Id does not exists"));

        ClientDetails ClientDetailsById = clientService.getClientByID(testId);

        Mockito.verify(clientDetailsDAO,Mockito.times(1)).getClientDetailsById(Mockito.any());
    }

}

