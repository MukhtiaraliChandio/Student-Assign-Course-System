package com.assign.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.assign.daos.ContactDao;
import com.assign.pojos.Contact;

public class ContactDaoImpl implements ContactDao{

	
SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();// here is set configuration.
	
@Override
public void saveContact(Contact contact) {

    Transaction tx = null;
    try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
        tx = session.beginTransaction();
        session.save(contact);   // Save Contact
        tx.commit();
    } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    }
}


	
	
	
}