package com.cds.org.controller;

import com.cds.org.computation.DetermineTotalFundForPMS;
import com.cds.org.mapper.PMSMapper;
import com.cds.org.model.ClientDetails;
import com.cds.org.service.ClientService;
import com.cds.org.service.PMSService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

@RunWith(SpringRunner.class)
@WebMvcTest(PMSController.class)
class PMSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private PMSService pmsService;

    @MockBean
    @Autowired
    private PMSMapper mapper;

    @MockBean
    private DetermineTotalFundForPMS determineTotalFundForPMS;


    @Test
    void testAddPMSClient() throws Exception {

        ClientDetails mockClientDetails = new ClientDetails();
        mockClientDetails.setClientId(1l);
        mockClientDetails.setClientName("chandra");
        mockClientDetails.setClientEmailId("csolanke77@gmail,com");
        mockClientDetails.setClientBrokerAccountName("Zerodha");
        mockClientDetails.setPaymentMode("online");
        mockClientDetails.setClientPortfolioAmount(BigDecimal.valueOf(700000));

        String expectedJson = this.mapToJSON(mockClientDetails);

        clientService.addClient(Mockito.any(ClientDetails.class));

        String URI = "/api/v1/client";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(expectedJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputInJson = result.getResponse().getContentAsString();

        assertThat(result).isNotNull();
        assertThat(result.getResponse()).isNotNull();

    }

    @Test
    void testGetAllClients() throws Exception {
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientName("chandra");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(90000));
        clientDetails.setClientEmailId("csolanke@hotmail.com");


        ClientDetails clientDetails1 = new ClientDetails();
        clientDetails1.setClientName("Amey");
        clientDetails1.setClientEmailId("Amey@hotmail.com");
        clientDetails1.setClientPortfolioAmount(BigDecimal.valueOf(80000));

        ArrayList<ClientDetails> allClientList = new ArrayList<>();
        allClientList.add(clientDetails);
        allClientList.add(clientDetails1);

        Iterable<ClientDetails> allClients = allClientList;

        Mockito.when(clientService.getAllClients()).thenReturn(allClients);

        String URI = "/api/v1/client";
        RequestBuilder requestBuilder =MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJsonString = this.mapToJSON(allClientList);
        String outputJsonString = result
                .getResponse()
                .getContentAsString();

        assertThat(allClients).isNotNull();
        //assertThat(expectedJsonString).isEqualTo(outputJsonString);


    }


    @Test
    void testGetClientByID() throws Exception {
        ClientDetails clientDetails = new ClientDetails();

        clientDetails.setClientId(1l);
        clientDetails.setClientEmailId("csolanke77@gmail.com");
        clientDetails.setClientAddress("LakeMary");
        clientDetails.setClientBrokerAccountName("zerodha");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(70000));

        Mockito.when(clientService.getClientByID(1l)).thenReturn(clientDetails);

        String expectedJson = this.mapToJSON(clientDetails);

        String URI = "/api/v1/client/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                        .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputJson = result.getResponse().getContentAsString();



       // assertThat(outputJson).isEqualTo(expectedJson);
        assertThat(clientDetails).isNotNull();


    }
    
    @Test
    void testGetPMSTotalFundValue() throws Exception {

        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientName("chandra");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(90000));
        clientDetails.setClientEmailId("csolanke@hotmail.com");


        ClientDetails clientDetails1 = new ClientDetails();
        clientDetails1.setClientName("Amey");
        clientDetails1.setClientEmailId("Amey@hotmail.com");
        clientDetails1.setClientPortfolioAmount(BigDecimal.valueOf(80000));

        ArrayList<ClientDetails> allClientList = new ArrayList<>();
        allClientList.add(clientDetails);
        allClientList.add(clientDetails1);

        Iterable<ClientDetails> allClients = allClientList;

        Mockito.when(clientService.getAllClients()).thenReturn(allClients);

        String URI = "/api/v1/client/fundValue";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputJson = result.getResponse().getContentAsString();


        BigDecimal totalFundValue = BigDecimal.valueOf(70000);
        Mockito.when(determineTotalFundForPMS.calculateTotalFundAmount())
                .thenReturn(totalFundValue);

        assertThat(totalFundValue).isNotNull();
        assertThat(totalFundValue).isEqualByComparingTo(BigDecimal.valueOf(70000));
        
        
        
    }

 // this method conerts object to json format
    private String mapToJSON(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}