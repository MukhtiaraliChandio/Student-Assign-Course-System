package com.assign.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.assign.dao.impl.CourseDaoImpl;
import com.assign.dao.impl.EnrollmentDaoImpl;
import com.assign.dao.impl.InstructorDaoImpl;
import com.assign.dao.impl.StudentDaoImpl;
import com.assign.pojos.Course;
import com.assign.pojos.Enrollment;
import com.assign.pojos.Instructor;
import com.assign.pojos.Student;

/**
 * Servlet implementation class EnrollmentServlet
 */
@WebServlet("/EnrollmentServlet")
public class EnrollmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    InstructorDaoImpl instDao=new   InstructorDaoImpl();
    CourseDaoImpl courseDao=new     CourseDaoImpl();
    EnrollmentDaoImpl enrollmentDao=new EnrollmentDaoImpl();
    StudentDaoImpl stdDao=new StudentDaoImpl();
    
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
	
		
		  String option=request.getParameter("option");
			
			if(option.equals("getInstructorsorCourses")) {
				
				try {
					 getInstructorfirstNamelastNamevalues(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(option.equals("submitenrollmentvalues")) {
				
				
				try {
					  
					  saveEnrollmentformValues(request, response);
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else if(option.equals("viewEnrollments")) {
				
				
				try {
					viewEnrollmentlistData(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(option.equals("getEnrolmentDatabyIdeditform")) {
				
				try {
					getEnrollmentbyid(request, response);
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(option.equals("updateForm")) {
				
			
					try {
						updateEnrolmentformValues(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}else if(option.equals("deleteEnrolmentListdatabyId")) {
				
				try {
					  deleteEnrolmentListdata(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
	     
	     
	     
	     
	
	}
 private void  getInstructorfirstNamelastNamevalues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

		
		
		List<Instructor> instructorList = instDao.getInstructorNameList();
	    request.setAttribute("instNames", instructorList);
	    System.out.println("TeacherNames:"+instructorList);

	    List<Course> courseList=courseDao.getAllCourses();
		request.setAttribute("courseListId", courseList);
		
		List<Student> studentsList=stdDao.getStudentNameList();
		request.setAttribute("studentList", studentsList);

		request.getRequestDispatcher("addenrollmentform.jsp")
		       .forward(request, response);

		 

}

private void  saveEnrollmentformValues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
 
	//Enrollment enrollment = new Enrollment();
	
	String studentIdStr = request.getParameter("studentId");
	String courseIdStr = request.getParameter("courseId");
	String instructorIdStr = request.getParameter("instructorId");
	String enrollmentDateStr=request.getParameter("enrollDate");
	
	
	int courseId = Integer.parseInt(courseIdStr);
	int instructorId = Integer.parseInt(instructorIdStr);
	int studentId=Integer.parseInt(studentIdStr);
	LocalDate enrollmentDate = LocalDate.parse(enrollmentDateStr);
	
	
	Session session = HibernateSessionFactory.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

	Student student = session.get(Student.class, studentId);
	Course course   = session.get(Course.class, courseId);

	Enrollment enrollment = new Enrollment();
	enrollment.setStudent(student);
	enrollment.setCourse(course);
	enrollment.setEnrollmentDate(enrollmentDate);

	
	if (enrollmentDao.isStudentAlreadyEnrolled(studentId, courseId)) {
	    // ❌ Do not save
	  
	} else {
	    // ✅ Save
		enrollmentDao.saveEnrollment(enrollment);
	}
	
	
	//session.save(enrollment);
	tx.commit();
	session.close();

	

	request.getRequestDispatcher("addenrollmentform.jsp")
    .forward(request, response);
	
	
}	
private void  viewEnrollmentlistData(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
	
	List<Enrollment> enrollmentList = enrollmentDao.getAllEnrollments();
	request.setAttribute("enrollments", enrollmentList);
	
	request.getRequestDispatcher("viewenrollmentdata.jsp").forward(request, response);
	
}
private void getEnrollmentbyid(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
	
	String idStr = request.getParameter("enrollmentId");

	    if (idStr == null || idStr.isEmpty()) {
	        response.sendRedirect("EnrollmentServlet?option=viewEnrollments");
	        return;
	    }

	    int enrollmentId = Integer.parseInt(idStr);
	    System.out.println("enrollmentId = " + enrollmentId);

	    Enrollment enrollment = enrollmentDao.getEnrollmentById(enrollmentId);

	   
	    request.setAttribute("courseListId", courseDao.getAllCourses());
	    request.setAttribute("studentList", stdDao.getStudentNameList());
	    request.setAttribute("enrollment", enrollment);
	    request.getRequestDispatcher("editenrollmentform.jsp")
	           .forward(request, response);
	    

}
private void updateEnrolmentformValues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

		
	int enrolmentId = Integer.parseInt(request.getParameter("enrollmentId"));
	String enrollmentIdStr = request.getParameter("enrollmentId");
	String studentIdStr = request.getParameter("studentId");
	String courseIdStr = request.getParameter("courseId");
	String instructorIdStr = request.getParameter("instructorId");
	String enrollmentDateStr = request.getParameter("enrollDate");

	int enrollmentId = Integer.parseInt(enrollmentIdStr);
	int studentId = Integer.parseInt(studentIdStr);
	int courseId = Integer.parseInt(courseIdStr);
	int instructorId = Integer.parseInt(instructorIdStr);

	LocalDate enrollmentDate = LocalDate.parse(enrollmentDateStr);
	

	// ===== DEBUG PRINTS =====
	System.out.println("Enrollment ID   : " + enrollmentId);
	System.out.println("Student ID      : " + studentId);
	System.out.println("Course ID       : " + courseId);
	System.out.println("Instructor ID   : " + instructorId);
	System.out.println("Enrollment Date : " + enrollmentDate);
	

	Session session = HibernateSessionFactory.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

	// ✅ Fetch existing enrollment
	Enrollment enrollment = session.get(Enrollment.class, enrollmentId);

	Student student = session.get(Student.class, studentId);
	Course course = session.get(Course.class, courseId);
	Instructor instructor = session.get(Instructor.class, instructorId);

	// ✅ Update fields
	enrollment.setStudent(student);
	enrollment.setCourse(course);
	enrollment.setInstructor(instructor);
	enrollment.setEnrollmentDate(enrollmentDate);

	// ❌ Do NOT check duplicate for same enrollment
	//session.update(enrollment);



	
	



	

	enrollmentDao.updateEnrollment(enrollment);
	
	//tx.commit();
	//session.close();


	request.getRequestDispatcher("editenrollmentform.jsp")
    .forward(request, response);
	
	
	
	
}
private void deleteEnrolmentListdata(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	int enrollmentId = Integer.parseInt(request.getParameter("enrollmentId"));

	System.out.println("enrollmentId:"+enrollmentId);
	
	enrollmentDao.deleteEnrollmentById(enrollmentId);

	List<Enrollment> enrollmentList = enrollmentDao.getAllEnrollments();
	request.setAttribute("enrollmentData", enrollmentList);

	request.getRequestDispatcher("viewenrollmentdata.jsp")
	       .forward(request, response);




}







}