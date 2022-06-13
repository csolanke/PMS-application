package com.cds.org.controller;


import com.cds.org.computation.DetermineTotalFundForPMS;
import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.mapper.PMSMapper;
import com.cds.org.security.AuthenticationRequest;
import com.cds.org.security.AuthenticationResponse;
import com.cds.org.model.ClientDetails;
import com.cds.org.security.JwtUtil;
import com.cds.org.service.ClientService;
import com.cds.org.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;


import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PMSController {


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    private ClientService service;
    private PMSMapper mapper;
    private DetermineTotalFundForPMS determineTotalFundForPMS;

    public PMSController(ClientService service,PMSMapper mapper,DetermineTotalFundForPMS determineTotalFundForPMS)
    {  this.service = service;
        this.mapper = mapper;
        this.determineTotalFundForPMS = determineTotalFundForPMS;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws BadCredentialsException {

      try {
             authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
          }
      catch (BadCredentialsException e) {
              throw new BadCredentialsException("Incorrect username and password ",e);
         }

          final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
          final String jwt = jwtUtil.generateToken(userDetails);


        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<ClientDetailsDTO> addPMSClient(@RequestBody @Valid ClientDetailsDTO dto)
    {
        ClientDetails clientDetails = mapper.clientDetailsDTOToEntity(dto);
        service.addClient(clientDetails);
        ClientDetailsDTO clientDetailsDTO = mapper.clientDetailsEntityToDto(clientDetails);
        return new ResponseEntity<>(clientDetailsDTO,HttpStatus.CREATED);
    }


   @GetMapping(value ="/client",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDetailsDTO>> getAllClients()
    {
        List<ClientDetails> clientDetails = service.getAllClients();

        List<ClientDetailsDTO> dtoList = getClientDetailsDTOList(clientDetails);
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }


    @GetMapping(value = "/client/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDetailsDTO> getClientByID(@PathVariable Long id) throws ResourceNotFoundException {

        ClientDetails clientByID = service.getClientByID(id);

        ClientDetailsDTO clientDetailsDTO = mapper.clientDetailsEntityToDto(clientByID);
        return new ResponseEntity<>(clientDetailsDTO,HttpStatus.OK);
    }


    @GetMapping("/fundValue")
    public ResponseEntity<BigDecimal> getPMSTotalFundValue()
    {
        BigDecimal calculatedSum = determineTotalFundForPMS.calculateTotalFundAmount();
        return new ResponseEntity<>(calculatedSum,HttpStatus.OK);
    }


    private List<ClientDetailsDTO> getClientDetailsDTOList(List<ClientDetails> clientDetails) {
        List<ClientDetailsDTO> dtoList = new ArrayList<>();
        for (ClientDetails clientDetail : clientDetails) {
            dtoList.add(mapper.clientDetailsEntityToDto(clientDetail));
        }
        return dtoList;
    }

}
