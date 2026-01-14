package com.assign.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.assign.daos.CourseDao;
import com.assign.pojos.Course;
import com.assign.pojos.Instructor;
import com.assign.pojos.User;

public class CourseDaoImpl implements CourseDao{

	
	
	
SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();// here is set configuration.
	
	
@Override
public void saveCourses(Course course) {
		    
		Transaction tx = null;
		try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
		        tx = session.beginTransaction();
		        session.save(course); // Save the Instructor
		        tx.commit();
		}catch (Exception e) {
		        if (tx != null) tx.rollback();
		        e.printStackTrace();
		}

		    
}// end of saveInstructor method.	

@Override
public List<Course> getAllCourses() {

    Session session = sessionFactory.openSession();
    List<Course> courseList = new ArrayList<>();

    try {
        session.beginTransaction();

        String hql = "SELECT c FROM Course c LEFT JOIN FETCH c.instructor";
        courseList = session.createQuery(hql, Course.class).getResultList();

        session.getTransaction().commit();

    } catch (Exception e) {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }

    return courseList;
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
public List<Course> getAllCoursesNames() {

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
public void updateAssignCourseSave(Course course) {

    Transaction tx = null;

    try (Session session = new Configuration()
            .configure()
            .buildSessionFactory()
            .openSession()) {

        tx = session.beginTransaction();
        session.update(course);   // UPDATE course
        tx.commit();

    } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    }
}



@Override
public void updateCourse(Course course) {

    Transaction tx = null;

    try (Session session = new Configuration()
            .configure()
            .buildSessionFactory()
            .openSession()) {

        tx = session.beginTransaction();
        session.update(course);   // UPDATE course
        tx.commit();

    } catch (Exception e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    }
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
public void deleteCourseById(int courseId) {

    Transaction tx = null;
    Session session = sessionFactory.openSession();

    try {
        tx = session.beginTransaction();

        // Fetch the Course object
        Course course = session.get(Course.class, courseId);

        if (course != null) {
            session.delete(course);   // DELETE
        } else {
            System.out.println("Course with ID " + courseId + " not found!");
        }

        tx.commit();

    } catch (Exception e) {
        if (tx != null) {
            tx.rollback();  // rollback if any error occurs
        }
        e.printStackTrace();
    } finally {
        session.close();    // always close session
    }
}



	
	
}