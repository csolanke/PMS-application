package com.cds.org.persistence;

import com.cds.org.model.ClientDetails;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientDetailsRepositoryTest {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;


    @Test
    @Order(1)
    @Rollback(value = false)
    public void testSaveClientDetails(){
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientName("Gajanan");
        clientDetails.setClientAddress("Pune");
        clientDetails.setClientBrokerAccountName("Upstox");
        clientDetails.setPaymentMode("Online");
        clientDetails.setPmsPurchasedDate(LocalDate.of(2022,01,01));

        clientDetailsRepository.save(clientDetails);

        Assertions.assertThat(clientDetails.getClientId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
   public  void testGetClientDetailsByID(){

        ClientDetails clientDetails1 = clientDetailsRepository.findById(1L).get();
        Assertions.assertThat(clientDetails1.getClientId()).isEqualTo(1L);
        Assertions.assertThat(clientDetails1.getClientName()).isEqualTo("Gajanan");

    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void testGetClientDetailsList(){

        ClientDetails clientDetails2 = new ClientDetails();
        clientDetails2.setClientName("Amey");
        clientDetails2.setClientAddress("LakeMary");
        clientDetails2.setClientBrokerAccountName("Zerodha");
        clientDetails2.setPaymentMode("Online");
        clientDetails2.setPmsPurchasedDate(LocalDate.of(2022,05,01));


        clientDetailsRepository.save(clientDetails2);
        ArrayList<ClientDetails> clientDetailsArrayList = new ArrayList<>();
        clientDetailsArrayList = (ArrayList<ClientDetails>) clientDetailsRepository.findAll();

        Assertions.assertThat(clientDetailsArrayList.size()).isEqualTo(2);

    }
    
    
    @Test
    @Order(4)
    @Rollback(value = false)
    public  void testUpdateClientDetails(){

        ClientDetails clientDetails1 = clientDetailsRepository.findById(1l).get();
          clientDetails1.setClientAddress("Aurangabad");
        ClientDetails clientDetailsUpdated = clientDetailsRepository.save(clientDetails1);

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

    }
}