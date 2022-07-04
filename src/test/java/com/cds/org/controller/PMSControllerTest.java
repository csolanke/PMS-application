
package com.cds.org.controller;

import com.cds.org.computation.DetermineTotalFundForPMS;
import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.dto.ClientDetailsIdentityDTO;
import com.cds.org.mapper.PMSMapper;
import com.cds.org.model.ClientDetails;
import com.cds.org.model.ClientDetailsIdentity;
import com.cds.org.security.AuthenticationRequest;
import com.cds.org.security.JwtUtil;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PMSControllerTest {
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter =objectMapper.writer();

    @Mock
    private ClientService clientService;

    @Mock
    private PMSMapper pmsMapper;

    @InjectMocks
    private PMSController pmsController;

    @Mock
    private DetermineTotalFundForPMS determineTotalFundForPMS;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserDetailsService userDetailsService;

    @Mock
    UserDetails userDetails;

    private ClientDetails clientDetails;
    private ClientDetailsDTO dto;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pmsController).build();

        ClientDetailsIdentity id = new ClientDetailsIdentity();
        id.setClientId(1l);
        id.setClientName("chandra");
        id.setClientEmailId("csolanke77@gmail.com");

        clientDetails = new ClientDetails();
        clientDetails.setId(id);
        clientDetails.setClientBrokerAccountName("Zerodha");
        clientDetails.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        clientDetails.setClientAddress("LakeMary");
      //  clientDetails.setPmsPurchasedDate(LocalDate.of(2022,06,14));
        clientDetails.setPaymentMode("Online");


        ClientDetailsIdentityDTO clientDetailsIdentityDTO = new ClientDetailsIdentityDTO();
        clientDetailsIdentityDTO.setClientId(1l);
        clientDetailsIdentityDTO.setClientEmailId("csolanke77@gmail.com");
        clientDetailsIdentityDTO.setClientName("chandra");

        dto = new ClientDetailsDTO();
        dto.setId(clientDetailsIdentityDTO);
        dto.setClientBrokerAccountName("Zerodha");
        dto.setClientPortfolioAmount(BigDecimal.valueOf(900000));
        dto.setClientAddress("LakeMary");
        //dto.setPmsPurchasedDate(LocalDate.of(2022,06,13));
        dto.setPaymentMode("Online");

    }


    @Test
    public void testGetAllClients() throws Exception {

        ClientDetails clientDetails1 = new ClientDetails();
        ClientDetailsIdentity id1 = new ClientDetailsIdentity();
        id1.setClientId(2l);
        id1.setClientName("Bhagya");
        id1.setClientEmailId("bhagya@gmail.com");
        clientDetails1.setId(id1);
        clientDetails1.setClientBrokerAccountName("Upstox");
        clientDetails1.setClientPortfolioAmount(BigDecimal.valueOf(600000));
        clientDetails1.setClientAddress("LakeMary");
        //clientDetails1.setPmsPurchasedDate(LocalDate.of(2022,06,13));
        clientDetails1.setPaymentMode("offline");

        List<ClientDetails> clientDetailsList = new ArrayList<>();
        clientDetailsList.add(clientDetails);
        clientDetailsList.add(clientDetails1);

        ClientDetailsDTO dto1 = new ClientDetailsDTO();

        ClientDetailsIdentityDTO identityDTO = new ClientDetailsIdentityDTO();
        identityDTO.setClientId(2l);
        identityDTO.setClientName("Bhagya");
        identityDTO.setClientEmailId("bhagya@gmail.com");


        dto1.setClientBrokerAccountName("Upstox");
        dto1.setClientPortfolioAmount(BigDecimal.valueOf(600000));
        dto1.setClientAddress("LakeMary");
        //clientDetails1.setPmsPurchasedDate(LocalDate.of(2022,06,13));
        dto1.setPaymentMode("offline");

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

        ClientDetailsIdentity id = new ClientDetailsIdentity();
        id.setClientId(1l);
        id.setClientName("chandra");
        id.setClientEmailId("csolanke77@gmail.com");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clientById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(id).getBytes(StandardCharsets.UTF_8))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

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

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDetails).getBytes(StandardCharsets.UTF_8))
                        .accept(MediaType.APPLICATION_JSON))
                       .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(201);
    }

    @Test
   public void testAuthenticateUserSuccessful() throws Exception {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest("Amey","password1");

        Mockito.when(userDetailsService.loadUserByUsername("Amey")).thenReturn(userDetails );
        Mockito.when(jwtUtil.generateToken(userDetails)).thenReturn("Bearer ywsjjkdhsjhjkhjkhjdshjkhjkdhsjkhjkz");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest).getBytes(StandardCharsets.UTF_8))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);

    }

   // this method converts object to json format
    private String mapToJSON(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }





}

