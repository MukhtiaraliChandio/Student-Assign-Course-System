package com.assign.daos;

import com.assign.pojos.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public interface AssignLoginDao {

	public User validateUser(String username, String password);
	
	
	
	
	
}