package com.cds.org.persistence;

import com.cds.org.model.PMSDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PMSDetailsRepository extends CrudRepository<PMSDetails,Long> {
}
