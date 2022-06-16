package com.cds.org.controller;

import com.cds.org.computation.DetermineTotalFundForPMS;
import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.mapper.PMSMapper;
import com.cds.org.model.ClientDetails;
import com.cds.org.security.JwtUtil;
import com.cds.org.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = PMSController.class)
public class PMSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper ;

    @MockBean
    private ClientService clientService;

    @MockBean
    private PMSMapper pmsMapper;

    @InjectMocks
    private PMSController pmsController;

    @MockBean
    private DetermineTotalFundForPMS determineTotalFundForPMS;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    UserDetailsService userDetailsService;

    private ClientDetails clientDetails;
    private ClientDetailsDTO dto;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pmsController).build();

        clientDetails = new ClientDetails();
        clientDetails.setClientName("chandra");
        clientDetails.setClientEmailId("csolanke77@gmail.com");
        clientDetails.setClientBrokerAccountName("Zerodha");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        clientDetails.setClientAddress("LakeMary");
        clientDetails.setClientId(1l);
      //  clientDetails.setPmsPurchasedDate(LocalDate.of(2022,06,14));
        clientDetails.setPaymentMode("Online");


        dto = new ClientDetailsDTO();
        dto.setClientName("chandra");
        dto.setClientEmailId("csolanke77@gmail.com");
        dto.setClientBrokerAccountName("Zerodha");
        dto.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        dto.setClientAddress("LakeMary");
        dto.setClientId(1l);
        //dto.setPmsPurchasedDate(LocalDate.of(2022,06,13));
        dto.setPaymentMode("Online");

    }


    @Test
    public void testGetAllClients() throws Exception {

        ClientDetails clientDetails1 = new ClientDetails();
        clientDetails1.setClientName("Bhagya");
        clientDetails1.setClientEmailId("bhagya@gmail.com");
        clientDetails1.setClientBrokerAccountName("Upstox");
        clientDetails1.setClientPortfolioAmount(BigDecimal.valueOf(600000));
        clientDetails1.setClientAddress("LakeMary");
        clientDetails1.setClientId(2l);
        //clientDetails1.setPmsPurchasedDate(LocalDate.of(2022,06,13));
        clientDetails1.setPaymentMode("offline");

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        clientDetailsList.add(clientDetails);
        clientDetailsList.add(clientDetails1);

        ClientDetailsDTO dto1 = new ClientDetailsDTO();
        dto1.setClientName("Bhagya");
        dto1.setClientEmailId("bhagya@gmail.com");
        dto1.setClientBrokerAccountName("Upstox");
        dto1.setClientPortfolioAmount(BigDecimal.valueOf(600000));
        dto1.setClientAddress("LakeMary");
        dto1.setClientId(2l);
        //clientDetails1.setPmsPurchasedDate(LocalDate.of(2022,06,13));
        dto1.setPaymentMode("offline");


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
        Assertions.assertThat(result.getResponse().getContentAsString()).contains("Bhagya");


    }

    @Test
    public void testGetClientDetailsByID() throws Exception {

        String mockedObjectToJson = mapToJSON(this .clientDetails);
        Mockito.when(clientService.getClientByID(1l)).thenReturn(this.clientDetails);

        //try to improve this mocking later
        Mockito.when(pmsMapper.clientDetailsEntityToDto(clientDetails)).thenReturn(dto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(mockedObjectToJson);
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void testGetTotalFundPMSFundValue() throws Exception {

        Mockito.when(determineTotalFundForPMS.calculateTotalFundAmount()).
                thenReturn(BigDecimal.valueOf(1500000));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fundValue")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        String fundValueAsString = result.getResponse().getContentAsString();

        Assertions.assertThat(fundValueAsString).contains("1500000");
        Assertions.assertThat(determineTotalFundForPMS.calculateTotalFundAmount())
                .isNotNull();
        Assertions.assertThat(determineTotalFundForPMS.calculateTotalFundAmount())
                .isEqualTo(BigDecimal.valueOf(1500000));

    }

    @Test
    public void testAddPMSClient() throws Exception {

        Mockito.when(clientService.addClient(clientDetails)).thenReturn(clientDetails);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDetails).getBytes(StandardCharsets.UTF_8))
                        .accept(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(201);
    }

   // this method converts object to json format
    private String mapToJSON(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    }
