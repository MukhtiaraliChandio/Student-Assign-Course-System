package com.assign.daos;

import java.util.List;

import com.assign.pojos.Student;
import com.assign.pojos.User;

public interface StudentDao {


	
	public User loginUser(String username, String password);
	
	public void saveUser(User user);
	
	public List<Student> getStudentNameList();
	
	public void saveStudent(Student std);

	public List<User> getAllUsers(); 
		
	public List<Student> getAllStudentsWithUsers(); 
	
	public Student getStudentById(int studentId);
	
	public void updateStudent(Student newData);
	
	public void deleteStudentById(int stdId);
	
	
	
}