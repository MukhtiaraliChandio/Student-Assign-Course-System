<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Course Assign</title>

<!-- Bootstrap -->
<link rel="stylesheet"
 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- Font Awesome -->
<link rel="stylesheet"
 href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
body {
    background-color: #f5f7fa;
}

.assign-card {
    background: #ffffff;
    padding: 30px;
    border-radius: 6px;
    box-shadow: 0 6px 15px rgba(0,0,0,0.1);
    margin: 40px auto;
    max-width: 500px;
}

.assign-title {
    text-align: center;
    color: #28a745;
    font-weight: bold;
    margin-bottom: 25px;
}

.form-control {
    height: 42px;
}

.input-group-addon {
    background: #f0f0f0;
}

.btn-submit {
    background-color: #28a745;
    color: #fff;
    width: 100%;
}

.btn-submit:hover {
    background-color: #218838;
}

.btn-reset {
    background-color: #6c757d;
    color: #fff;
    width: 100%;
    margin-top: 10px;
}
</style>
</head>

<body>
<div class="assign-card">
    <h3 class="assign-title">
        <i class="fa fa-book"></i> Edit Enrollment Form
    </h3>

    <form action="EnrollmentServlet" method="post">

        <!-- REQUIRED FOR UPDATE -->
        <input type="hidden" name="option" value="updateForm">
        <input type="hidden" name="enrollmentId"
               value="${enrollment.enrollmentId}">

     <!-- ================= Instructor ================= -->
   <label>Instructor</label>
   <input type="text" class="form-control" readonly
       value="${enrollment.instructor.firstName} ${enrollment.instructor.lastName}">

  <input type="hidden" name="instructorId"
       value="${enrollment.instructor.instId}">
     
     
     
     
            <!-- ================= Student ================= -->
        <label>Student</label>
        <input type="text" class="form-control" readonly
               value="${enrollment.student.firstName}${enrollment.student.lastName}">

        <input type="hidden" name="studentId"
               value="${enrollment.student.stdId}">
     


        <!-- ================= Course ================= -->
        <label>Course</label>
        <input type="text" class="form-control" readonly
               value="${enrollment.course.courseName}">

        <input type="hidden" name="courseId"
               value="${enrollment.course.courseId}">


 


        <!-- ================= Enrollment Date ================= -->
        <div class="form-group">
            <label>Enrollment Date</label>
            <div class="input-group">
                <span class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                </span>
                <input type="date"
                       name="enrollDate"
                       class="form-control"
                       value="${enrollment.enrollmentDate}"
                       required>
            </div>
        </div>

        <!-- ================= Buttons ================= -->
        <button type="submit" class="btn btn-submit">
            <i class="fa fa-save"></i> Update Data
        </button>

    </form>
</div>


</body>
</html>
