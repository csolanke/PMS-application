package com.cds.org.persistence;

import com.cds.org.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepository{


    @Autowired
    private SessionFactory factory;
    public Session getSession(){
        Session session = factory.getCurrentSession();

        if(session==null){
            session = factory.openSession();
        }
        return session;
    }

    public User fetchUserByName(String name){
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", name));
       return (User) criteria.list().get(0);

    }

}
