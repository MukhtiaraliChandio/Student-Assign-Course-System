package com.assign.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assign.dao.impl.*;
import com.assign.pojos.*;


/**
 * Servlet implementation class AssignLoginServlet
 */
@WebServlet("/AssignLoginServlet")
public class AssignLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    StudentDaoImpl stdDao=new StudentDaoImpl();
    AssignLoginDaoImpl assignDao=new AssignLoginDaoImpl();
    

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
		//doGet(request, response);
		
		PrintWriter out = response.getWriter();
		
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		
		
		System.out.println("User Name:"+userName+"User Password:"+userPassword);
		
		User user = assignDao.validateUser(userName, userPassword);
		

		
        if (user != null) {
            // Valid login
        	
        	 HttpSession session = request.getSession();
             session.setAttribute("user", user);
             
             out.println("<script>");
		     out.println("alert('Login successful!');");
             
             if ("admin".equalsIgnoreCase(user.getRole())) {
         		
         		System.out.print("admin");
         		
                 response.sendRedirect("admindashboard.html");

         	}else if ("instructor".equalsIgnoreCase(user.getRole())) {
        		
        		System.out.print("instructor");
        		
                response.sendRedirect("instructordashboard.html");

        	}else if ("student".equalsIgnoreCase(user.getRole())) {
            	
            	System.out.print("student");
                
       		    response.sendRedirect("studentdashboard.html");

               
            }
           

           
        } else {
            // Invalid login
           
        	  out.println("<script>");
              out.println("alert('Invalid email or password');");
            
              out.println("</script>");
        }
	
		
		
	}

}
