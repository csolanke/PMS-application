package com.cds.org.computation;

import com.cds.org.model.ClientDetails;
import com.cds.org.service.ClientService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DetermineTotalFundForPMS {

    ClientService clientService;

    public DetermineTotalFundForPMS(ClientService clientService)
    {
     this.clientService = clientService;
    }


public BigDecimal calculateTotalFundAmount()
  {
    Iterable<ClientDetails> allClients =  this.clientService.getAllClients();
    List<ClientDetails> clientDetails = StreamSupport.stream(allClients.spliterator(), false)
            .collect(Collectors.toList());

     return  clientDetails.stream()
              .filter(Objects::nonNull)
              .map(ClientDetails::getClientPortfolioAmount)
             .reduce(BigDecimal.ZERO,BigDecimal::add);
  }
}
