package com.cds.org.controller;


import com.cds.org.computation.DetermineTotalFundForPMS;
import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.mapper.PMSMapper;
import com.cds.org.security.AuthenticationRequest;
import com.cds.org.security.AuthenticationResponse;
import com.cds.org.model.ClientDetails;
import com.cds.org.security.JwtUtil;
import com.cds.org.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;


import java.util.List;
import java.util.stream.StreamSupport;

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
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) throws Exception {

      try {
             authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
          }
      catch (BadCredentialsException e) {
              throw new Exception("Incorrect username and password ",e);
         }

          final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
          final String jwt = jwtUtil.generateToken(userDetails);


        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }


    @PostMapping("/client")
    public ResponseEntity<ClientDetailsDTO> addPMSClient(@RequestBody ClientDetailsDTO dto)
    {
        ClientDetails clientDetails = mapper.clientDetailsDTOToEntity(dto);
        service.addClient(clientDetails);
        ClientDetailsDTO clientDetailsDTO = mapper.clientDetailsEntityToDto(clientDetails);
        return new ResponseEntity<>(clientDetailsDTO,HttpStatus.CREATED);
    }


    @GetMapping("/client")
    public ResponseEntity<List<ClientDetailsDTO>> getAllClients()
    {
        Iterable<ClientDetails> allClients = service.getAllClients();
        List<ClientDetails> clientDetails = StreamSupport.stream(allClients.spliterator(), false)
                .collect(Collectors.toList());

        List<ClientDetailsDTO> dtoList = new ArrayList<>();
        for (ClientDetails clientDetail : clientDetails) {
            dtoList.add(mapper.clientDetailsEntityToDto(clientDetail));
        }
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }


    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDetailsDTO> getClientByID(@PathVariable Long id){

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

}
