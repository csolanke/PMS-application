package com.cds.org.controller;


import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.mapper.PMSMapper;
import com.cds.org.model.ClientDetails;
import com.cds.org.service.PMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class PMSController {

    @Autowired
    private PMSService service;

    @Autowired
    private PMSMapper mapper;

    @PostMapping("/client")
    public void addPMSClient(@RequestBody ClientDetailsDTO dto)
    {
        ClientDetails clientDetails = mapper.clientDetailsDTOToEntity(dto);
        service.addClient(clientDetails);

    }
}
