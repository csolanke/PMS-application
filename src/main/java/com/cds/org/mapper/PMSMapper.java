package com.cds.org.mapper;

import com.cds.org.dto.ClientDetailsDTO;
import com.cds.org.dto.ClientDetailsIdentityDTO;
import com.cds.org.model.ClientDetails;
import com.cds.org.model.ClientDetailsIdentity;
import org.springframework.stereotype.Component;

@Component
public class PMSMapper {

    public ClientDetails clientDetailsDTOToEntity(ClientDetailsDTO dto)
    {

        ClientDetails clientDetails = new ClientDetails();
        if(null!=dto){
            clientDetails.setId(clientDetailsIdentityDtoToIdentity(dto.getId()));
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
            dto.setId(clientDetailsIdentityToDTO(clientDetails.getId()));
            dto.setClientAddress(clientDetails.getClientAddress());
            dto.setPmsPurchasedDate(clientDetails.getPmsPurchasedDate());
            dto.setClientBrokerAccountName(clientDetails.getClientBrokerAccountName());
            dto.setClientPortfolioAmount(clientDetails.getClientPortfolioAmount());
            dto.setPaymentMode(clientDetails.getPaymentMode());

        }

        return dto;
    }

    public ClientDetailsIdentity clientDetailsIdentityDtoToIdentity(ClientDetailsIdentityDTO dto){
        ClientDetailsIdentity clientDetailsIdentity = new ClientDetailsIdentity();
        if(null!=dto){
            clientDetailsIdentity.setClientId(dto.getClientId());
            clientDetailsIdentity.setClientName(dto.getClientName());
            clientDetailsIdentity.setClientEmailId(dto.getClientEmailId());

        }
        return clientDetailsIdentity;
    }

    public ClientDetailsIdentityDTO clientDetailsIdentityToDTO(ClientDetailsIdentity clientDetailsIdentity){

        ClientDetailsIdentityDTO dto = new ClientDetailsIdentityDTO();
        if(null!= clientDetailsIdentity){
            dto.setClientId(clientDetailsIdentity.getClientId());
            dto.setClientName(clientDetailsIdentity.getClientName());
            dto.setClientEmailId(clientDetailsIdentity.getClientEmailId());
        }
        return dto;
    }


}
