package com.cds.org.controller;


import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.mapper.PMSMapper;
import com.cds.org.model.ClientDetails;
import com.cds.org.service.PMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;


import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/")
public class PMSController {

    @Autowired
    private PMSService service;

    @Autowired
    private PMSMapper mapper;

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
}
