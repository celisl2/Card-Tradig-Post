<jsp:useBean id="bean" class="beans.SessionBean" scope="session"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
		<link rel="stylesheet" href="styles/styles.css" type="text/css"/>
	</head>
	<body>
		<div class="center">
		<center><h1>Registration</h1></center>
			<center><p style="color: red"><%= bean.getMessage()  %></p></center>
			<form action="RegistrationControllerServlet" method="POST">
				<label>Username: </label>
				<input type="text" name="userName">
				<br />
				<label>Password: </label>
				<input type="text" name="password">
				<br />
				<label>Confirm: </label>
				<input type="text" name="passwordConfirm">
				<div class="flx">
					<input type="submit" value="Register">
					<a href="login.jsp">Back to Login</a>
				</div>
				<input type="hidden" name="pageFrom" value="registration.jsp">
		</form>
	</div>
	</body>
</html>
<% bean.setMessage("");  %>