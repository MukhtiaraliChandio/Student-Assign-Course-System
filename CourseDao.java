package com.assign.daos;

import java.util.List;

import  com.assign.pojos.*;

public interface CourseDao {

	
	public void saveCourses(Course  course);
	
	public List<Course> getAllCourses(); 
	
	public void updateAssignCourseSave(Course course);

	public void updateCourse(Course course);
		
	public Course getCourseById(int courseId); 
	
	public void deleteCourseById(int courseId); 
	
	public List<Instructor> getInstructorNameList(); 

	public List<Course> getAllCoursesNames(); 
	
	
	
	
	
}