package com.assign.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.assign.daos.EnrollmentDao;
import com.assign.pojos.Enrollment;
import com.assign.servlets.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class EnrollmentDaoImpl implements EnrollmentDao{

SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();// here is set configuration.
		
@Override
public void saveEnrollment(Enrollment enrollment) {

	    Transaction tx = null;

	    try (Session session = new Configuration()
	            .configure()
	            .buildSessionFactory()
	            .openSession()) {

	        tx = session.beginTransaction();

	        session.save(enrollment);   // Save Enrollment

	        tx.commit();

	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    }


}
public boolean isStudentAlreadyEnrolled(int studentId, int courseId) {

    Session session = sessionFactory.openSession();
    boolean exists = false;

    try {
        String hql = "SELECT count(e) FROM Enrollment e " +
                     "WHERE e.student.id = :studentId " +
                     "AND e.course.id = :courseId";

        Long count = session.createQuery(hql, Long.class)
                .setParameter("studentId", studentId)
                .setParameter("courseId", courseId)
                .uniqueResult();

        exists = (count != null && count > 0);

    } finally {
        session.close();
    }

    return exists;
}


@Override
public List<Enrollment> getAllEnrollments(){

    Session session = sessionFactory.openSession();
    List<Enrollment> enrollmentList = new ArrayList<>();

    try {
        session.beginTransaction();

        String hql =
                "SELECT DISTINCT e FROM Enrollment e " +
                "LEFT JOIN FETCH e.student " +
                "LEFT JOIN FETCH e.course " +
                "LEFT JOIN FETCH e.instructor";


        enrollmentList =
            session.createQuery(hql, Enrollment.class).getResultList();

        session.getTransaction().commit();

    } catch (Exception e) {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }

    return enrollmentList;
}
@Override
public Enrollment getEnrollmentById(int enrollmentId) {

    Session session = sessionFactory.openSession();
    Enrollment enrollment = null;

    try {
        enrollment = session.get(Enrollment.class, enrollmentId);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
    }

    return enrollment;
}
	

public void updateEnrollment(Enrollment enrollment) {

    Transaction tx = null;
    Session session = HibernateSessionFactory.getSessionFactory().openSession();

    try {
        tx = session.beginTransaction();

        session.update(enrollment);

        tx.commit();

    } catch (Exception e) {
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }
}
@Override
public void deleteEnrollmentById(int enrollmentId) {

    Transaction tx = null;
    Session session = sessionFactory.openSession();

    try {
        tx = session.beginTransaction();

        Enrollment enrollment = session.get(Enrollment.class, enrollmentId);

        if (enrollment != null) {
            session.delete(enrollment);   // DELETE Enrollment
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


	
	
}