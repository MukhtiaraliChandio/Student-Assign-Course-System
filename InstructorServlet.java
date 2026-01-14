package com.assign.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.pojos.Course;
import com.assign.pojos.Instructor;
import com.assign.pojos.User;
import com.assign.dao.*;
import com.assign.dao.impl.*;


/**
 * Servlet implementation class InstructorServlet
 */
@WebServlet("/InstructorServlet")
public class InstructorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstructorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    InstructorDaoImpl instDao=new   InstructorDaoImpl();
    CourseDaoImpl courseDao=new     CourseDaoImpl();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String option=request.getParameter("option");
		
		if(option.equals("insertValues")) {
			
			try {
				insertInstructordataAnduserData(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(option.equals("showInstlistData")) {
			
			
			 try {
				    tofetchallvaluesInstructordataAnduserData(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
		}else if(option.equals("getInstructorNames")) {
			
			
			try {
				getInstructorfirstNamelastNamevalues(request, response);
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(option.equals("getDatabyIdeditForm")) {
			
			try {
				getInstructordataByid(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(option.equals("updateData")) {
			
			
			try {
				updateInstructorformVaues(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(option.equals("deleteData")) {
			
			
			try {
				deleteInstructorlistData(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		
		
}
/// end  void doPost method.
private void  insertInstructordataAnduserData(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
	
		
	
	
	User    user=new  User();
		
	user.setUserName(request.getParameter("userName"));
	user.setUserPassword(request.getParameter("userPassword"));
	  
	
	  
	  
	  Instructor instructor = new Instructor();
	  
     instructor.setFirstName(request.getParameter("firstName"));
     instructor.setLastName(request.getParameter("lastName"));
     instructor.setSurname(request.getParameter("surname"));
     instructor.setFatherName(request.getParameter("fatherName"));
     instructor.setContactNumber(request.getParameter("contactNumber"));
     instructor.setCnic(request.getParameter("cnic"));
     instructor.setEmail(request.getParameter("email"));
     instructor.setDepartment(request.getParameter("department"));
     instructor.setGender(request.getParameter("gender"));
     instructor.setAddress(request.getParameter("address"));


     user.setRole("INSTRUCTOR");
     
     instructor.setUser(user); // here is set user for userId.
     
     
    
     instDao.saveUser(user);// auto generated id save.
     instDao.saveInstructor(instructor); // hibernate automatic set userid in  student table.
     
     
     response.sendRedirect("addinstructorform.html"); // Or return a message/forward to JSP	
		
		
	
	
	
		
		
}/// end of insertInstructordataAnduserData method.

private void  tofetchallvaluesInstructordataAnduserData(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{


	
	Instructor inst=new Instructor();
	List <Instructor> listinstData=instDao.getAllInstructorsWithUsers();
	request.setAttribute("instData", listinstData);
	System.out.print(listinstData);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("viewinstructordata.jsp");
	dispatcher.forward(request, response);
	
	
	
	
    
    
    

}

private void  getInstructorfirstNamelastNamevalues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	
	
	List<Instructor> instructorList = instDao.getInstructorNameList();
    request.setAttribute("instNames", instructorList);
    System.out.println("TeacherNames:"+instructorList);

    List<Course> courseList=courseDao.getAllCourses();
	request.setAttribute("courseListId", courseList);
	
    
	request.getRequestDispatcher("assigncourseform.jsp")
	       .forward(request, response);

	 

}
private void getInstructordataByid(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	Instructor inst=new Instructor();
	
	int instId = Integer.parseInt(request.getParameter("instId"));

	 
     Instructor instructor = instDao.getInstructorById(instId);

     request.setAttribute("instructor", instructor);
     
     request.getRequestDispatcher("editinstructorform.jsp").forward(request, response);

	
}
private void updateInstructorformVaues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	
	 Instructor instructor = new Instructor();
	  
	 int instId = Integer.parseInt(request.getParameter("instId"));
	 
	 instructor.setInstId(instId); 
     instructor.setFirstName(request.getParameter("firstName"));
     instructor.setLastName(request.getParameter("lastName"));
     instructor.setSurname(request.getParameter("surname"));
     instructor.setFatherName(request.getParameter("fatherName"));
     instructor.setContactNumber(request.getParameter("contactNumber"));
     instructor.setCnic(request.getParameter("cnic"));
     instructor.setEmail(request.getParameter("email"));
     instructor.setDepartment(request.getParameter("department"));
     instructor.setGender(request.getParameter("gender"));
     instructor.setAddress(request.getParameter("address"));



     
     instDao.updateInstructor(instructor);
     response.sendRedirect("editinstructorform.jsp"); // Or return a message/forward to JSP	
		
		
}
private void deleteInstructorlistData(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	int instId = Integer.parseInt(request.getParameter("instId"));

    instDao.deleteInstructorById(instId);
  
    List <Instructor> listinstData=instDao.getAllInstructorsWithUsers();
	request.setAttribute("instData", listinstData);
	
    //response.sendRedirect("instructorviewdata.jsp");
    
    
    request.getRequestDispatcher("instructorviewdata.jsp").forward(request, response);

}


	

}
