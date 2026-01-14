package com.assign.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.dao.impl.ContactDaoImpl;
import com.assign.pojos.Contact;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    ContactDaoImpl contactDao = new ContactDaoImpl();
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
		
	
		Contact contact = new Contact();
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String mobileNumber=request.getParameter("mobileNumber");
		String message=request.getParameter("messageValue");

		
	    System.out.println("\nFirstName:"+firstName+"\nLastName:"+lastName+"\nEmail:"+email+"\nMobileNumber:"+mobileNumber+"\nMessage:"+message);

		
	   contact.setFirstName(firstName);
	   contact.setLastName(lastName);
	   contact.setEmail(email);
	   contact.setPhone(mobileNumber);
	   contact.setMessage(message);

	    
	    contactDao.saveContact(contact);

	    response.sendRedirect("contact.html");
	
	
	}

}
