package com.assign.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.assign.pojos.Contact;
import com.assign.pojos.Course;
import com.assign.pojos.Enrollment;
import com.assign.pojos.Instructor;
import com.assign.pojos.Student;
import com.assign.pojos.User;

import java.util.Date;

public class TestMain {
    public static void main(String[] args) {
       
    	Configuration cfg = new Configuration().configure(); // Load hibernate.cfg.xml
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // --- Create and Save User for Student ---
        User userStudent = new User();

        userStudent.setUserName("john_doe");
        userStudent.setUserPassword("password123");
        userStudent.setRole("ROLE_STUDENT");

        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setSurname("JD");
        student.setFatherName("Michael Doe");
        student.setContactNumber("03001234567");
        student.setCnic("12345-6789012-3");
        student.setEmail("john@example.com");
        student.setProgram("BSCS");
        student.setGender("Male");
        student.setAddress("Street 123, City");
        student.setUser(userStudent);

        session.save(userStudent);
        session.save(student);

        // --- Create and Save User for Instructor ---
        User userInstructor = new User();

        userInstructor.setUserName("jane_smith");
        userInstructor.setUserPassword("pass456");
        userInstructor.setRole("ROLE_INSTRUCTOR");

        Instructor instructor = new Instructor();
        instructor.setFirstName("Jane");
        instructor.setLastName("Smith");
        instructor.setSurname("JS");
        instructor.setFatherName("David Smith");
        instructor.setContactNumber("03009876543");
        instructor.setCnic("98765-4321098-7");
        instructor.setEmail("jane@example.com");
        instructor.setDepartment("Computer Science");
        instructor.setGender("Female");
        instructor.setAddress("Block 456, City");
        instructor.setUser(userInstructor);

        session.save(userInstructor);
        session.save(instructor);

        // --- Create and Save Course ---
        Course course = new Course();
        course.setCourseName("Object Oriented Programming");
        course.setCourseCode("CS101");
        course.setCreditHours(3);
        course.setInstructor(instructor); // Link instructor to course

        session.save(course);

        // --- Create and Save Enrollment ---
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);     // Link student
        enrollment.setCourse(course);       // Link course
        enrollment.setEnrollmentDate(null); // Set today's date

        session.save(enrollment);

       
        Contact contact = new Contact();

        contact.setFirstName("Mukhtiar");
        contact.setLastName("Ali");
        contact.setEmail("mukhtiar@example.com");
        contact.setPhone("03021135133");
        contact.setMessage("I want details about course registration");

        session.save(contact);   // INSERT into contact table

        // --- Commit Transaction ---
        tx.commit();
        session.close();
        factory.close();

        System.out.println("Data inserted successfully into User, Student, Instructor, Course, and Enrollment tables!");
    }
}
