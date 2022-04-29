package com.cds.org.persistence;

import com.cds.org.model.ClientDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends CrudRepository<ClientDetails,Long>
{

}
