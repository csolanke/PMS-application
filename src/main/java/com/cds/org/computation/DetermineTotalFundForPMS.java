package com.cds.org.computation;

import com.cds.org.model.ClientDetails;
import com.cds.org.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component
public class DetermineTotalFundForPMS {

    @Autowired
    ClientService clientService;

    public DetermineTotalFundForPMS(ClientService clientService)
    {
     this.clientService = clientService;
    }

    public DetermineTotalFundForPMS() {
        //empty constructor
    }

    public BigDecimal calculateTotalFundAmount()
  {
      List<ClientDetails> clientDetails = this.clientService.getAllClients();
     return  clientDetails.stream()
              .filter(Objects::nonNull)
              .map(ClientDetails::getClientPortfolioAmount)
             .reduce(BigDecimal.ZERO,BigDecimal::add);
  }
}
