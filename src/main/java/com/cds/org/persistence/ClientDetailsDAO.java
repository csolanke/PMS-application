package com.cds.org.persistence;

import com.cds.org.exceptions.ClientDetailsNotFoundException;
import com.cds.org.model.ClientDetails;
import com.cds.org.model.ClientDetailsIdentity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientDetailsDAO {

    @Autowired
    private SessionFactory factory;
    public Session getSession(){
        Session session = factory.getCurrentSession();

        if(session==null){
            session = factory.openSession();
        }
        return session;
    }


    public ClientDetails saveClientDetails(ClientDetails clientDetails){
        getSession().save(clientDetails);
        return clientDetails;
    }

    public List<ClientDetails> getAllClientDetails(){
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ClientDetails> query = criteriaBuilder.createQuery(ClientDetails.class);
        Root<ClientDetails> root = query.from(ClientDetails.class);
        CriteriaQuery<ClientDetails> all = query.select(root);
        Query<ClientDetails> allQuery = session.createQuery(all);

        return  allQuery.getResultList();

    }

    public ClientDetails getClientDetailsById(ClientDetailsIdentity id) throws ClientDetailsNotFoundException {
         Session session = getSession();
        ClientDetails clientDetails = session.get(ClientDetails.class, id);
        if(clientDetails==null){
            throw new ClientDetailsNotFoundException("ClientDetails not found for client id " +id);
        }

        return clientDetails;
    }
}
