package com.assign.daos;

import java.util.List;

import com.assign.pojos.Enrollment;

public interface EnrollmentDao {

	public void saveEnrollment(Enrollment enrollment); 	
	
	public List<Enrollment> getAllEnrollments();
	
	public Enrollment getEnrollmentById(int enrollmentId);
	
	public void updateEnrollment(Enrollment enrollment);
	
	public void deleteEnrollmentById(int enrollmentId);
	
	public boolean isStudentAlreadyEnrolled(int studentId, int courseId);
	
	
}