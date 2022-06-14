package com.cds.org.persistence;

import com.cds.org.model.ClientDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

        return getSession().createCriteria(ClientDetails.class).list();
    }

    public ClientDetails getClientDetailsById(Long id){
         Session session = getSession();
        return session.get(ClientDetails.class, id);
    }
}
