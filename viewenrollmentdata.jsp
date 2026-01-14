<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enrollment List</title>

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
    <div class="table-title">Enrollment List</div>

    <table>
        <tr>
            
            <th>Enrollment ID</th>
            <th>Instructor Name </th>
            <th>Student Name </th>
            <th>Course </th>
            <th>Enrollment Date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach var="e" items="${enrollments}">
            <tr>
                <td>${e.enrollmentId}</td>
                <td>${e. instructor.firstName}${e. instructor.lastName}</td>
                <td>${e.student.firstName}${e.student.lastName}</td>
                <td>${e.course.courseName}</td>
                <td>${e.enrollmentDate}</td>

                <td>
                      <a href="EnrollmentServlet?option=getEnrolmentDatabyIdeditform&enrollmentId=${e.enrollmentId}"> Edit Data             </td>
                <td>
                    <a href="EnrollmentServlet?option=deleteEnrolmentListdatabyId&enrollmentId=${e.enrollmentId}">Delete
                    </a>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>
