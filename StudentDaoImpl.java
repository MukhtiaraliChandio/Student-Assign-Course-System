package com.assign.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.assign.daos.StudentDao;
import com.assign.pojos.Student;
import com.assign.pojos.User;

public class StudentDaoImpl implements StudentDao{


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
public void saveStudent(Student std) {
	    
	Transaction tx = null;
	try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
	        tx = session.beginTransaction();
	        session.save(std); // Save the Instructor
	        tx.commit();
	}catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	}

	    
}// end of saveInstructor method.

@Override
public List<Student> getAllStudentsWithUsers() {

    Session session = sessionFactory.openSession();
    List<Student> studentList = new ArrayList<>();

    try {
        session.beginTransaction();

        // HQL: fetch Student + related User
        String hql = "SELECT s FROM Student s LEFT JOIN FETCH s.user";
        studentList = session.createQuery(hql, Student.class).getResultList();

        session.getTransaction().commit();

    } catch (Exception e) {

        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        e.printStackTrace();

    } finally {
        session.close();
    }

    return studentList;
}

@Override
public List<Student> getStudentNameList() {

    Session session = sessionFactory.openSession();
    List<Student> students = new ArrayList<>();

    try {
        String hql = "FROM Student";
        Query<Student> query = session.createQuery(hql, Student.class);
        students = query.list();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
    }

    return students;
}

@Override
public Student getStudentById(int studentId) {

    Session session = sessionFactory.openSession();
    Student student = null;

    try {
        student = session.get(Student.class, studentId);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
    }

    return student;
}


@Override
public List<User> getAllUsers() {
    List<User> users = null;
    Session session = null;

    try {
        session = sessionFactory.openSession();  // factory = SessionFactory
        users = session.createQuery("FROM User", User.class).list(); // HQL to fetch all users
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (session != null) session.close();
    }

    return users;
}

@Override
public User loginUser(String userName, String userPassword) {
    Session session = sessionFactory.openSession();
    User user = null;

    try {
        String hql = "FROM users WHERE userName = :userName  AND  userPassword  = :userPassword";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("userName", userName);
        query.setParameter("userPassword  ", userPassword);

        user = query.uniqueResult(); // null if not found
    } finally {
        session.close();
    }
    return user;
}
@Override
public void updateStudent(Student newData) {

    Transaction tx = null;

    try {
        Session session = new Configuration()
                .configure()
                .buildSessionFactory()
                .openSession();

        tx = session.beginTransaction();

        Student student = session.get(
                Student.class, newData.getStdId()
        );

        if (student == null) {
            throw new RuntimeException("Student not found!");
        }

        // Update fields
        student.setFirstName(newData.getFirstName());
        student.setLastName(newData.getLastName());
        student.setSurname(newData.getSurname());
        student.setFatherName(newData.getFatherName());
        student.setContactNumber(newData.getContactNumber());
        student.setCnic(newData.getCnic());
        student.setEmail(newData.getEmail());
        student.setProgram(newData.getProgram());
        student.setGender(newData.getGender());
        student.setAddress(newData.getAddress());

        session.update(student);
        tx.commit();

    } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    }
}

	
@Override
public void deleteStudentById(int stdId) {

    Transaction tx = null;
    Session session = sessionFactory.openSession();

    try {
        tx = session.beginTransaction();

        Student student = session.get(Student.class, stdId);

        if (student != null) {
            session.delete(student);   // DELETE
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