package com.assign.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.dao.impl.CourseDaoImpl;
import com.assign.dao.impl.InstructorDaoImpl;
import com.assign.pojos.*;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    CourseDaoImpl courseDao=new     CourseDaoImpl();
    InstructorDaoImpl instDao=new   InstructorDaoImpl();
    
    
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
		
       String option=request.getParameter("option");
		
		if(option.equals("insertCoursevalues")) {
			
			try {
				  addCoursevalues(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(option.equals("viewCourses")) {
			
			
			try {
				  viewCoursevalues(request, response);
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(option.equals("assignCoursevalues")) {
			
			
			try {
				  assignCourseformDatasave(request, response);
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(option.equals("getDatabyIdeditForm")) {
			
			
			try {
				getCoursedataByid(request, response);
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(option.equals("updateCourse")) {
			
			
			 try {
				
				 updateCourseformValues(request, response);
			
			 }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(option.equals("deleteCoursebyId")) {
			
			 try {
					
				 deleteCourselistValuesbyId(request, response);
			
			 }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
	    
    }
	
private void  addCoursevalues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
	

		
		Course course=new Course();
	
		String courseName=request.getParameter("courseName");
		String courseCode=request.getParameter("courseCode");
		String creditHours=request.getParameter("creditHours");
		String semester=request.getParameter("semester");
		
		int ch=Integer.parseInt(creditHours);
	    int semst=Integer.parseInt(semester);
	    int credHrs=Integer.parseInt(creditHours);
		
	    course.setCourseCode(courseCode);
	    course.setCourseName(courseName);
	    course.setSemester(ch);
	    course.setCreditHours(ch);
	    

	    courseDao.saveCourses(course);
	    
	    response.sendRedirect("addcourseform.html");
		
	
	}
	
	
private void  viewCoursevalues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{


    List<Course> courseList = courseDao.getAllCourses();
    request.setAttribute("courseData", courseList);

    RequestDispatcher rd = request.getRequestDispatcher("viewcourses.jsp");
    rd.forward(request, response);
    

}
private void  assignCourseformDatasave(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	String courseIdStr = request.getParameter("courseId");
	String instructorIdStr = request.getParameter("instructorId");
     
	String courseName = request.getParameter("courseName");
	String courseCode = request.getParameter("courseCode");
	String creditHoursStr = request.getParameter("creditHours");
	String semesterStr = request.getParameter("semester");

	// DEBUG
	System.out.println("InstructorId=" + instructorIdStr);
	System.out.println("CourseId=" + courseIdStr);
	System.out.println("courseName=" + courseName);
	System.out.println(" creditHoursStr=" +  creditHoursStr);
	System.out.println(" creditHoursStr=" +  creditHoursStr);
	System.out.println(" semesterStr=" +  semesterStr);
	
	// Validation
	if (courseIdStr == null || instructorIdStr == null ||
	    courseIdStr.isEmpty() || instructorIdStr.isEmpty()) {
	    throw new RuntimeException("CourseId or InstructorId is missing");
	}

	int courseId = Integer.parseInt(courseIdStr);
	int instructorId = Integer.parseInt(instructorIdStr);
	int creditHours = Integer.parseInt(creditHoursStr);
	int semester = Integer.parseInt(semesterStr);

	// Set relations (BEST PRACTICE)
	Instructor instructor = instDao.getInstructorById(instructorId);
	Course course = courseDao.getCourseById(courseId);

	course.setInstructor(instructor);
	course.setCourseName(courseName);
    course.setCourseCode(courseCode);
	course.setCreditHours(creditHours);
	course.setSemester(semester);

	courseDao.updateAssignCourseSave(course);


    // redirect to list page
    response.sendRedirect("assigncourseform.jsp");
	
	
	 

}
private void  getCoursedataByid(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	
	Course course = new Course();

	int courseId = Integer.parseInt(request.getParameter("courseId"));

	Course courseObj = courseDao.getCourseById(courseId);

	
	List<Instructor> instructorList = instDao.getInstructorNameList();
    request.setAttribute("instNames", instructorList);
    System.out.println("TeacherNames:"+instructorList);

    List<Course> courseList=courseDao.getAllCourses();
	request.setAttribute("courseListId", courseList);
	
    
	request.setAttribute("course", courseObj);


	
	
	request.getRequestDispatcher("editcourseform.jsp")
	       .forward(request, response);

	
	
}

private void  updateCourseformValues(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
	
	String courseIdStr = request.getParameter("courseId");
	String instructorIdStr = request.getParameter("instructorId");
    String courseName = request.getParameter("courseName");
	String courseCode = request.getParameter("courseCode");
	String creditHoursStr = request.getParameter("creditHours");
	String semesterStr = request.getParameter("semester");

	// DEBUG
	System.out.println("InstructorId=" + instructorIdStr);
	System.out.println("CourseId=" + courseIdStr);
	System.out.println("courseName=" + courseName);
	System.out.println(" creditHoursStr=" +  creditHoursStr);
	System.out.println(" creditHoursStr=" +  creditHoursStr);
	System.out.println(" semesterStr=" +  semesterStr);
	
	// Validation
	if (courseIdStr == null || instructorIdStr == null ||
	    courseIdStr.isEmpty() || instructorIdStr.isEmpty()) {
	    throw new RuntimeException("CourseId or InstructorId is missing");
	}

	int courseId = Integer.parseInt(courseIdStr);
	int instructorId = Integer.parseInt(instructorIdStr);
	int creditHours = Integer.parseInt(creditHoursStr);
	int semester = Integer.parseInt(semesterStr);

	// Set relations (BEST PRACTICE)
	Instructor instructor = instDao.getInstructorById(instructorId);
	Course course = courseDao.getCourseById(courseId);

	course.setInstructor(instructor);
	course.setCourseName(courseName);
    course.setCourseCode(courseCode);
	course.setCreditHours(creditHours);
	course.setSemester(semester);

	courseDao.updateCourse(course);



    // redirect to list page
	request.getRequestDispatcher("editcourseform.jsp")
    .forward(request, response);
	
	
	
}
private void  deleteCourselistValuesbyId(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{

	// 1. Get courseId from request
	int courseId = Integer.parseInt(request.getParameter("courseId"));

	// 2. Delete the course
	courseDao.deleteCourseById(courseId);

	// 3. Fetch updated list of courses
	List<Course> listCourseData = courseDao.getAllCourses();
	request.setAttribute("courseData", listCourseData);

	// 4. Forward to course view JSP
	request.getRequestDispatcher("viewcourses.jsp").forward(request, response);


}	



}