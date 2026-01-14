<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Instructor Data</title>


<style>
body {
    background-color: #f4f6f9;
    font-family: Arial, Helvetica, sans-serif;
}

.table-card {
    background: #ffffff;
    width: 95%;
    margin: 40px auto;
    padding: 25px;
    border-radius: 6px;
    box-shadow: 0 0 12px rgba(0,0,0,0.08);
}

.table-title {
    text-align: center;
    color: #28a745;
    font-size: 22px;
    font-weight: bold;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th {
    background-color: #28a745;
    color: #fff;
    padding: 10px;
    text-align: center;
}

td {
    padding: 9px;
    border: 1px solid #dee2e6;
    text-align: center;
}

a {
    color: #28a745;
    font-weight: bold;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>

<div class="table-card">
<div class="table-title"> List Of Course Data</div>
<table border="1" cellpadding="5">


<tr>
    <th>Course ID</th>
    <th>Course Name</th>
    <th>Course Code</th>
    <th>Credit Hours</th>
    <th>Semester</th>
    <th>Instructor</th>
    <th>Edit</th>
    <th>Delete</th>
</tr>

<c:forEach var="course" items="${courseData}">
<tr>
    <td>${course.courseId}</td>
    <td>${course.courseName}</td>
    <td>${course.courseCode}</td>
    <td>${course.creditHours}</td>
    <td>Semester ${course.semester}</td>
    <td>
         <c:if test="${course.instructor != null}">
                        ${course.instructor.firstName}
                        ${course.instructor.lastName}
                    </c:if>
                    <c:if test="${course.instructor == null}">
                        Not Assigned
                    </c:if>
    </td>

    <td>
        
           <a href="CourseServlet?option=getDatabyIdeditForm&courseId=${course.courseId}">
            Edit Data
        </a>
    </td>

    <td>
        <a href="CourseServlet?option=deleteCoursebyId&courseId=${course.courseId}">
            Delete Data
        </a>
    </td>
</tr>
</c:forEach>

</table>
</div>

</body>
</html>
