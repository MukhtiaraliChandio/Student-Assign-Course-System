package com.assign.dao.impl;

import com.assign.daos.AssignLoginDao;
import com.assign.pojos.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class AssignLoginDaoImpl  implements AssignLoginDao{

SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();// here is set configuration.
	
	
@Override
public User validateUser(String username, String password) {
	        Transaction tx = null;
	        User user = null;

	        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
	            tx = session.beginTransaction();

	            String hql = "FROM User WHERE username = :username AND  userPassword  = :password";
	            Query<User> query = session.createQuery(hql, User.class);
	            query.setParameter("username", username);
	            query.setParameter("password", password);

	            user = query.uniqueResult();

	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	        }

	        return user;
	    }	
	
	
	
	
}