package com.cds.org.persistance;

import com.cds.org.model.ClientDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PMSRepository extends CrudRepository<ClientDetails,Long>
{

}
