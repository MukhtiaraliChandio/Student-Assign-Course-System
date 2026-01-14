<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Course Registration</title>

<link rel="stylesheet"
 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<link rel="stylesheet"
 href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
body {
    background-color: #f5f7fa;
}
.register-card {
    background: #ffffff;
    padding: 30px;
    border-radius: 6px;
    box-shadow: 0 6px 15px rgba(0,0,0,0.1);
    margin-top: 30px;
}
.register-title {
    color: #28a745;
    font-weight: bold;
}
.form-control {
    height: 42px;
}
.input-group-addon {
    background: #f0f0f0;
}
.btn-register {
    background-color: #28a745;
    color: #fff;
}
.btn-register:hover {
    background-color: #218838;
}
.btn-reset {
    background-color: #6c757d;
    color: #fff;
}
</style>
</head>

<body>

<div class="container">
<div class="row">
<div class="col-md-6 col-md-offset-3">

<div class="register-card">

<h3 class="text-center register-title">
<i class="fa fa-book"></i>Assign Course Form 
</h3>

<form action="CourseServlet" method="post">
<input type="hidden" name="option" value="assignCoursevalues">

<div class="form-group">
    <label>Select Instructor:</label>
    <select name="instructorId" class="form-control" required>
     <c:forEach var="instructor" items="${instNames}">
            <option value="${instructor.instId}">
                ${instructor.firstName} ${instructor.lastName}
            </option>
        </c:forEach>
    </select>
</div>


<div class="form-group">
    <label>Select Course:</label>
    <select name="courseId" class="form-control" required>
     <c:forEach var="course" items="${courseListId}">
            <option value="${course.courseId}">
                ${course.courseName} (${course.courseCode})
            </option>
        </c:forEach>
    </select>
</div>


<!-- Buttons -->
<div class="form-group text-center">
<button type="submit" class="btn btn-update btn-lg">
<i class="fa fa-save"></i>Data Submit
</button>

</div>

</form>
</div>
</div>
</div>
</div>

</body>
</html>
