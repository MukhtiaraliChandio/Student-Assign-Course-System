package com.assign.servlets;

import org.hibernate.SessionFactory;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.assign.pojos.*;

public class HibernateSessionFactory {

	  private static SessionFactory sessionFactory;
	
	public static SessionFactory  getSessionFactory() {
		
		 Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		 
		 sessionFactory = cfg.buildSessionFactory();
		 
		 return sessionFactory;
		 
	}
	
	
}
