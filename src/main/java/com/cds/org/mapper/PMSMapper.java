package com.cds.org.mapper;

import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.model.ClientDetails;
import org.springframework.stereotype.Component;

@Component
public class PMSMapper {

    public ClientDetails clientDetailsDTOToEntity(ClientDetailsDTO dto)
    {

        ClientDetails clientDetails = new ClientDetails();
        if(null!=dto)
        {
            clientDetails.setClientId(dto.getClientId());
            clientDetails.setClientName(dto.getClientName());
            clientDetails.setClientEmailId(dto.getClientEmailId());
            clientDetails.setClientAddress(dto.getClientAddress());
            clientDetails.setClientBrokerAccountName(dto.getClientBrokerAccountName());
            clientDetails.setPaymentMode(dto.getPaymentMode());
            clientDetails.setPmsPurchasedDate(dto.getPmsPurchasedDate());
            clientDetails.setClientPortfolioAmount(dto.getClientPortfolioAmount());

        }

        return clientDetails;

    }



    public ClientDetailsDTO clientDetailsEntityToDto(ClientDetails clientDetails)
    {
        ClientDetailsDTO dto = new ClientDetailsDTO();

        if(clientDetails!=null)
        {
            dto.setClientId(clientDetails.getClientId());
            dto.setClientName(clientDetails.getClientName());
            dto.setClientAddress(clientDetails.getClientAddress());
            dto.setPmsPurchasedDate(clientDetails.getPmsPurchasedDate());
            dto.setClientBrokerAccountName(clientDetails.getClientBrokerAccountName());
            dto.setClientPortfolioAmount(clientDetails.getClientPortfolioAmount());
            dto.setPaymentMode(clientDetails.getPaymentMode());
            dto.setClientEmailId(clientDetails.getClientEmailId());

        }

        return dto;
    }


}
