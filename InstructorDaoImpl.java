package com.assign.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.assign.daos.InstructorDao;
import com.assign.pojos.Course;
import com.assign.pojos.Instructor;
import com.assign.pojos.User;




public class InstructorDaoImpl implements InstructorDao {

	
SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();// here is set configuration.
	
	
@Override
public void saveUser(User user) {
		    
		Transaction tx = null;
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
		        tx = session.beginTransaction();
		        session.save(user); // Save the Instructor
		        tx.commit();
		}catch (Exception e) {
		        if (tx != null) tx.rollback();
		        e.printStackTrace();
		}

		    
}// end of saveInstructor method.	


@Override
public void saveInstructor(Instructor instructor) {
		    
		Transaction tx = null;
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
		        tx = session.beginTransaction();
		        session.save(instructor); // Save the Instructor
		        tx.commit();
		}catch (Exception e) {
		        if (tx != null) tx.rollback();
		        e.printStackTrace();
		}

		    
}// end of saveInstructor method.

@Override
public List<Instructor> getAllInstructorsWithUsers() {
    Session session = sessionFactory.openSession();
    List<Instructor> instructorsList = new ArrayList<>();

    try {
        session.beginTransaction();

        // HQL query to fetch Instructor + related User
        String hql = "SELECT i FROM Instructor i LEFT JOIN FETCH i.user";
        instructorsList = session.createQuery(hql, Instructor.class).getResultList();

        session.getTransaction().commit();
    } catch (Exception e) {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }

    return instructorsList;
}


@Override
public List<Instructor> getInstructorNameList() {

	 Session session = sessionFactory.openSession();

    List<Instructor> instructors = new ArrayList<>();

    try {
        String hql = "FROM Instructor";
        Query<Instructor> query = session.createQuery(hql, Instructor.class);
        instructors = query.list();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
    }

    return instructors;
}
@Override
public List<Course> getAllCourses() {

    Session session = sessionFactory.openSession();
    List<Course> courses = new ArrayList<>();

    try {
        String hql = "FROM Course";
        Query<Course> query = session.createQuery(hql, Course.class);
        courses = query.list();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
    }

    return courses;
}

@Override
public Course getCourseById(int courseId) {

    Session session = sessionFactory.openSession();
    Course course = null;

    try {
        course = session.get(Course.class, courseId);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
    }

    return course;
}




@Override
public Instructor getInstructorById(int instId) {

    Session session = sessionFactory.openSession();
    Instructor instructor = null;

    try {
        instructor = session.get(Instructor.class, instId);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
    }

    return instructor;
}



@Override
public void updateInstructor(Instructor newData) {

    Transaction tx = null;

    try {
    	Session session = new Configuration().configure().buildSessionFactory().openSession();  
    			
    

        tx = session.beginTransaction();

        Instructor instructor = session.get(
                Instructor.class, newData.getInstId()
        );

        if (instructor == null) {
            throw new RuntimeException("Instructor not found!");
        }

        // Update fields
        instructor.setFirstName(newData.getFirstName());
        instructor.setLastName(newData.getLastName());
        instructor.setSurname(newData.getSurname());
        instructor.setFatherName(newData.getFatherName());
        instructor.setContactNumber(newData.getContactNumber());
        instructor.setCnic(newData.getCnic());
        instructor.setEmail(newData.getEmail());
        instructor.setDepartment(newData.getDepartment());
        instructor.setGender(newData.getGender());
        instructor.setAddress(newData.getAddress());

        session.update(instructor);

        tx.commit();

    } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    }
}

@Override
public void deleteInstructorById(int instId) {

    Transaction tx = null;
    Session session = sessionFactory.openSession();

    try {
        tx = session.beginTransaction();

        Instructor instructor = session.get(Instructor.class, instId);

        if (instructor != null) {
            session.delete(instructor);   // DELETE
        }

        tx.commit();

    } catch (Exception e) {
        if (tx != null) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }
}











	
	
}// end of InstructorDaoImpl class.