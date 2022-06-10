package com.cds.org.controller;

import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.mapper.PMSMapper;
import com.cds.org.model.ClientDetails;
import com.cds.org.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class PMSControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ClientService clientService;

    @Mock
    private PMSMapper pmsMapper;

    @InjectMocks
    private PMSController pmsController;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pmsController).build();
    }

    @Test
    public void testGetAllClients() throws Exception {


        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientName("chandra");
        clientDetails.setClientEmailId("csolanke77@gmail.com");
        clientDetails.setClientBrokerAccountName("Zerodha");

        ClientDetails clientDetails1 = new ClientDetails();
        clientDetails1.setClientName("Bhagya");
        clientDetails1.setClientEmailId("bhagya@gmail.com");
        clientDetails1.setClientBrokerAccountName("Upstox");

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        clientDetailsList.add(clientDetails);
        clientDetailsList.add(clientDetails1);


        ClientDetailsDTO dto = new ClientDetailsDTO();
        dto.setClientName("chandra");
        dto.setClientEmailId("csolanke77@gmail.com");
        dto.setClientBrokerAccountName("Zerodha");
        dto.setClientId(1l);


        ClientDetailsDTO dto1 = new ClientDetailsDTO();
        dto1.setClientName("chandra");
        dto1.setClientEmailId("csolanke77@gmail.com");
        dto1.setClientBrokerAccountName("Zerodha");
        dto1.setClientId(1l);


        String inputMockedtoString = mapToJSON(clientDetailsList);

        Mockito.when(clientService.getAllClients()).thenReturn(clientDetailsList);

        Mockito.when(pmsMapper.clientDetailsEntityToDto(clientDetails)).thenReturn(dto);
        Mockito.when(pmsMapper.clientDetailsEntityToDto(clientDetails1)).thenReturn(dto1);



        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/client")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getResponse().getContentAsString()).contains("chandra");


    }

    @Test
    public void testGetClientDetailsByID() throws Exception {
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientName("chandra");
        clientDetails.setClientEmailId("csolanke77@gmail.com");
        clientDetails.setClientBrokerAccountName("Zerodha");
        clientDetails.setClientId(1l);


        ClientDetailsDTO dto = new ClientDetailsDTO();
        dto.setClientName("chandra");
        dto.setClientEmailId("csolanke77@gmail.com");
        dto.setClientBrokerAccountName("Zerodha");
        dto.setClientId(1l);

        String mockedObjectToJson = mapToJSON(clientDetails);

        Mockito.when(clientService.getClientByID(1l)).thenReturn(clientDetails);

        //try to improve this mocking later
        Mockito.when(pmsMapper.clientDetailsEntityToDto(clientDetails)).thenReturn(dto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(mockedObjectToJson);
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

   // this method conerts object to json format
    private String mapToJSON(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    }
