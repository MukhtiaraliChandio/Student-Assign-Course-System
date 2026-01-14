package com.assign.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.pojos.Student;
import com.assign.pojos.User;
import com.assign.dao.*;
import com.assign.dao.impl.*;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    StudentDaoImpl stdDao=new StudentDaoImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		///response.getWriter().append("Served at: ").append(request.getContextPath());
		    this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	

		String option=request.getParameter("option");
		
		if(option.equals("insertValue")) {
			
			 try {
			
				 insertStudentdataAnduserData(request, response);
			
			 }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
		}else if(option.equals("showData")) {
			
			try {
				showAllstudentsdata(request, response);
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(option.equals("getStudentdatabyIdeditForm")) {
			
			try {
				getStudentdataByid(request,response);
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(option.equals("updateStudent")) {
		   
			try {
				updateStudentformVaues(request,response);
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}else if(option.equals("deleteStudentdataByid")) {
			
			
			try {
				   deletetudentlistData(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
}
private void  insertStudentdataAnduserData(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
			
			  User    user=new  User();

			  String userName=request.getParameter("userName");
			  String userPassword=request.getParameter("userPassword");
			  
			 // user.setUserId(userId); 
			  user.setUserName(userName);
			  user.setUserPassword(userPassword);
			   
			    

		      String firstName = request.getParameter("firstName");
		      String lastName = request.getParameter("lastName");
		      String surname = request.getParameter("surname");
		      String fatherName = request.getParameter("fatherName");
		      String contactNumber = request.getParameter("contactNumber");
		      String cnic = request.getParameter("cnic");
		      String email = request.getParameter("email");
		      String program = request.getParameter("program");
		      String gender = request.getParameter("gender");
		      String address = request.getParameter("address");


		      // Create Student object
		      Student student = new Student();
		     
		      
		      
		      student.setFirstName(firstName);
		      student.setLastName(lastName);
		      student.setSurname(surname);
		      student.setFatherName(fatherName);
		      student.setContactNumber(contactNumber);
		      student.setCnic(cnic);
		      student.setEmail(email);
		      student.setProgram(program);
		      student.setGender(gender);
		      student.setAddress(address);

		 
		      user.setRole("STUDENT");
		      student.setUser(user); // 
		      
		      
		      // Save Student
		     //studentDao.saveStudent(student);

		      stdDao.saveUser(user);  // auto generated id save.
		      stdDao.saveStudent(student); // hibernate automatic set userid in  student table.
		      
		      
		      // Redirect after success
		      response.sendRedirect("addstudentform.html");
			  
			  
			  
			
			
				
}/// end of insertStudentdata method.

private void  showAllstudentsdata(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

	Student student = new Student();

	List<Student> studentData =  stdDao.getAllStudentsWithUsers();
	request.setAttribute("studentData", studentData);

	System.out.print(studentData);

	RequestDispatcher dispatcher=request.getRequestDispatcher("studentviewdata.jsp");dispatcher.forward(request, response);

	
}//// end of showAllstudentsdata method.

private void  getStudentdataByid(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

	
	 int studentId = Integer.parseInt(request.getParameter("stdId"));

     // 2. Call DAO method
     Student student = stdDao.getStudentById(studentId);

     // 3. Send data to JSP
     request.setAttribute("student", student);

     // 4. Forward to edit page
     request.getRequestDispatcher("editstudentform.jsp")
            .forward(request, response);


}
private void updateStudentformVaues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	
	   Student student = new Student();

	    String stdIdStr = request.getParameter("stdId");

	    if (stdIdStr == null || stdIdStr.isEmpty()) {
	        throw new RuntimeException("Student ID is missing from request");
	    }

	    student.setStdId(Integer.parseInt(stdIdStr));

	    student.setFirstName(request.getParameter("firstName"));
	    student.setLastName(request.getParameter("lastName"));
	    student.setSurname(request.getParameter("surname"));
	    student.setFatherName(request.getParameter("fatherName"));
	    student.setContactNumber(request.getParameter("contactNumber"));
	    student.setCnic(request.getParameter("cnic"));
	    student.setEmail(request.getParameter("email"));
	    student.setProgram(request.getParameter("program"));
	    student.setGender(request.getParameter("gender"));
	    student.setAddress(request.getParameter("address"));

        stdDao.updateStudent(student);

        // Redirect after success
    response.sendRedirect("editstudentform.jsp");
	  
	  
}
private void deletetudentlistData(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
	
	int stdId = Integer.parseInt(request.getParameter("stdId"));

	stdDao.deleteStudentById(stdId);

	List<Student> listStdData = stdDao.getAllStudentsWithUsers();
	request.setAttribute("stdData", listStdData);



	request.getRequestDispatcher("studentviewdata.jsp")
	       .forward(request, response);

	
	
}	
	
}