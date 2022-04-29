package com.cds.org.service;

import com.cds.org.model.PMSDetails;
import com.cds.org.persistence.PMSDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PMSService {
    @Autowired
     PMSDetailsRepository repository;

    public void addFund(PMSDetails details)
    {
      repository.save(details);
    }


}
