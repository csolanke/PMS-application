


package com.cds.org.persistence;

import com.cds.org.exceptions.ClientDetailsNotFoundException;
import com.cds.org.model.ClientDetails;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientDetailsDAOTest {

    @Autowired
    private ClientDetailsDAO clientDetailsDAO;


    @Test
    @Order(1)
    @Rollback(value = false)
       void testSaveClientDetails(){
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientAddress("Pune");
        clientDetails.setClientBrokerAccountName("Upstox");
        clientDetails.setPaymentMode("Online");
        clientDetails.setPmsPurchasedDate(LocalDate.of(2022,01,01));

        ClientDetails clientDetails1 = clientDetailsDAO.saveClientDetails(clientDetails);

        Assertions.assertThat(clientDetails1.getId().getClientId()).isPositive();
    }

   /* @Test
    @Order(2)
    @Rollback(value = false)
    void testGetClientDetailsByID() throws ClientDetailsNotFoundException {

        ClientDetails clientDetails1 = clientDetailsDAO.getClientDetailsById(1l);
        Assertions.assertThat(clientDetails1.getClientId()).isEqualTo(1L);
        Assertions.assertThat(clientDetails1.getClientName()).isEqualTo("Gajanan");

    }

    @Test
    @Order(3)
    @Rollback(value = false)
        void testGetClientDetailsList(){

        ClientDetails clientDetails2 = new ClientDetails();
        clientDetails2.setClientName("Amey");
        clientDetails2.setClientAddress("LakeMary");
        clientDetails2.setClientBrokerAccountName("Zerodha");
        clientDetails2.setPaymentMode("Online");
        clientDetails2.setPmsPurchasedDate(LocalDate.of(2022,05,01));


        clientDetailsDAO.saveClientDetails(clientDetails2);
        ArrayList<ClientDetails> clientDetailsArrayList = new ArrayList<>();
        clientDetailsArrayList = (ArrayList<ClientDetails>) clientDetailsDAO.getAllClientDetails();

        Assertions.assertThat(clientDetailsArrayList).hasSize(2);

    }
    
    
    @Test
    @Order(4)
    @Rollback(value = false)
     void testUpdateClientDetails() throws ClientDetailsNotFoundException {

        ClientDetails clientDetails1 = clientDetailsDAO.getClientDetailsById(1l);
          clientDetails1.setClientAddress("Aurangabad");
        ClientDetails clientDetailsUpdated = clientDetailsDAO.saveClientDetails(clientDetails1);

        Assertions.assertThat(clientDetailsUpdated.getClientAddress()).isEqualTo("Aurangabad");

    }


@Test
    @Order(5)
    @Rollback(value = false)
    public void testDeleteClientDetailsById(){

        clientDetailsRepository.deleteById(1l);
         ClientDetails clientDetails = null;
        Optional<ClientDetails> ClientDetailsByID = clientDetailsRepository.findById(1l);

        if(ClientDetailsByID.isPresent()){
         clientDetails = ClientDetailsByID.get();
        }

        Assertions.assertThat(clientDetails).isNull();

    }*/

}


