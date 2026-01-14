<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
<div class="table-title"> List Of Student Data</div>
<table border="1" cellpadding="5">



<tr>
    <th>User ID</th>
    <th>Student ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Father Name</th>
    <th>Surname</th>
    <th>CNIC</th>
    <th>Contact Number</th>
    <th>Gender</th>
    <th>Email</th>
    <th>Program</th>
    <th>Address</th>
    <th>Edit</th>
    <th>Delete</th>
</tr>

<!-- JSTL loop for Students -->
<c:forEach var="student" items="${studentData}">
<tr>
    <td>${student.user.userId}</td>
    <td>${student.stdId}</td>
    <td>${student.firstName}</td>
    <td>${student.lastName}</td>
    <td>${student.fatherName}</td>
    <td>${student.surname}</td>
    <td>${student.cnic}</td>
    <td>${student.contactNumber}</td>
    <td>${student.gender}</td>
    <td>${student.email}</td>
    <td>${student.program}</td>
    <td>${student.address}</td>

    <td>
        <a href="StudentServlet?option=getStudentdatabyIdeditForm&stdId=${student.stdId}">
            Edit Data
        </a>
    </td>
    <td>
        <a href="StudentServlet?option=deleteStudentdataByid&stdId=${student.stdId}">
            Delete Data
        </a>
    </td>
</tr>
</c:forEach>

</table>
</div>

</body>
</html>
