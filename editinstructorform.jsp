<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Update Instructor</title>

<!-- Bootstrap 3 CSS -->
<link rel="stylesheet"
 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- Font Awesome -->
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
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
        margin-top: 30px;
    }
    .register-title {
        color: #28a745;
        font-weight: bold;
        margin-bottom: 10px;
    }
    .form-control {
        height: 42px;
        border-radius: 4px;
    }
    .input-group-addon {
        background: #f0f0f0;
    }
    textarea.form-control {
        height: auto;
    }
    .btn-update {
        background-color: #28a745;
        border-color: #28a745;
        color: #fff;
    }
    .btn-update:hover {
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
    <i class="fa fa-edit"></i> Update Instructor
</h3>
<p class="text-center">Modify instructor information below</p>

<form action="InstructorServlet" method="post">

<input type="hidden" name="option" value="updateData">


<!---Instructor Id -->
<div class="form-group">
<label>Instructor Id</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-user"></i></span>
<input type="text" name="instId" class="form-control"
       value="${instructor.instId}" required>
</div>
</div>

<!-- First Name -->
<div class="form-group">
<label>First Name</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-user"></i></span>
<input type="text" name="firstName" class="form-control"
       value="${instructor.firstName}" required>
</div>
</div>

<!-- Last Name -->
<div class="form-group">
<label>Last Name</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-user"></i></span>
<input type="text" name="lastName" class="form-control"
       value="${instructor.lastName}" required>
</div>
</div>

<!-- Surname -->
<div class="form-group">
<label>Surname</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-user"></i></span>
<input type="text" name="surname" class="form-control"
       value="${instructor.surname}">
</div>
</div>

<!-- Father Name -->
<div class="form-group">
<label>Father's Name</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-male"></i></span>
<input type="text" name="fatherName" class="form-control"
       value="${instructor.fatherName}">
</div>
</div>

<!-- Contact -->
<div class="form-group">
<label>Contact Number</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-phone"></i></span>
<input type="text" name="contactNumber" class="form-control"
       value="${instructor.contactNumber}">
</div>
</div>

<!-- CNIC -->
<div class="form-group">
<label>CNIC</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-id-card"></i></span>
<input type="text" name="cnic" class="form-control"
       value="${instructor.cnic}">
</div>
</div>

<!-- Email -->
<div class="form-group">
<label>Email</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
<input type="email" name="email" class="form-control"
       value="${instructor.email}">
</div>
</div>

<!-- Department -->
<div class="form-group">
<label>Department</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-building"></i></span>
<input type="text" name="department" class="form-control"
       value="${instructor.department}">
</div>
</div>

<!-- Gender -->
<div class="form-group">
<label>Gender</label><br>
<label class="radio-inline">
<input type="radio" name="gender" value="Male"
<c:if test="${instructor.gender == 'Male'}">checked</c:if>> Male
</label>
<label class="radio-inline">
<input type="radio" name="gender" value="Female"
<c:if test="${instructor.gender == 'Female'}">checked</c:if>> Female
</label>
</div>

<!-- Address -->
<div class="form-group">
<label>Address</label>
<div class="input-group">
<span class="input-group-addon"><i class="fa fa-home"></i></span>
<textarea name="address" class="form-control" rows="3">
${instructor.address}
</textarea>
</div>
</div>

<!-- Buttons -->
<div class="form-group text-center">
<button type="submit" class="btn btn-update btn-lg">
<i class="fa fa-save"></i> Update Data
</button>

</div>

</form>
</div>
</div>
</div>
</div>

</body>
</html>
