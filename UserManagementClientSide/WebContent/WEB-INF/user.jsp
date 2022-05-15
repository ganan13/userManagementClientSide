<%@page import="dao.dbConnection"%>
<%@page import="resources.user"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Management Services</title>
	
	<!-- Linking the css scripts -->
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<link rel="stylesheet" href="Views/form.css">
	
	<!-- Linking the js files -->
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/user.js"></script>
	
</head>
<body>

	<% dbConnection userConn = new dbConnection(); %>
		
	<div class="container">
	<div class="row">
	<div class="col-12">
		<h1 align="center">User Management Services</h1>
		
		<!--------------------- Start of form  ------------------------------->
		<form id="formCon" name="formCon">
			<input id="userId" name="userId" type="text" class="form-control form-control-sm" placeholder="userId">
			<br> 
		
			<input id="name" name="name" type="text" class="form-control form-control-sm" placeholder="name">
			<br> 
            
            <input id="dob" name="dob" type="text" class="form-control form-control-sm" placeholder="dob">
			<br> 
			
			<input id="nicNo" name="nicNo" type="text" class="form-control form-control-sm" placeholder="nicNo">
			<br> 
			<input id="phoneNo" name="phoneNo" type="text" class="form-control form-control-sm" placeholder="phoneno">
			<br> 
			<input id="email" name="email" type="text" class="form-control form-control-sm" placeholder="email">
			<br> 
			
			<input id="address" name="address" type="text" class="form-control form-control-sm" placeholder="address">
			<br> 
			<input id="password" name="password" type="text" class="form-control form-control-sm" placeholder="password">
			<br> 
            
			<input id="btnSave" name="btnSave" type="button" value="Add User" class="btn btn-primary">
            
		</form>
		<!--------------------- End of form  ------------------------------->
		
		<br>
		<!--------------------- Alerts  ------------------------------->
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<!--------------------- Display concepts  ------------------------------->
		<div id="divItemsGrid">
		<%
			user userObj = new user();
			out.print(userObj.readUser());
		%>
		</div>

	</div>
	</div> 
	</div>
</body>
</html>