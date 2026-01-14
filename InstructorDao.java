package com.assign.daos;


import java.util.List;

import com.assign.pojos.Course;
import com.assign.pojos.Instructor;
import com.assign.pojos.User;

public interface InstructorDao {

	
public void saveUser(User user);
	

public void saveInstructor(Instructor instructor);

	
public List<Instructor> getAllInstructorsWithUsers(); 


public List<Instructor> getInstructorNameList();

public List<Course> getAllCourses(); 

public Course getCourseById(int courseId); 


public Instructor getInstructorById(int instId); 

public void updateInstructor(Instructor instructor);


public void deleteInstructorById(int instId); 


}