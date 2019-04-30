<jsp:useBean id="bean" class="beans.SessionBean" scope="session"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log In</title>
		<link rel="stylesheet" href="styles/styles.css" type="text/css"/>
	</head>
	<body>
		<div class="center">
			<center><h1>Card Trading Post</h1></center>
			<!-- check if null then dont display anything -->
			<center><p style="color: red"><%= bean.getMessage()  %></p></center>
			<form action="LoginControllerServlet" method="POST">
				<div class="flexME">
					<label>Username: </label>
					<input type="text" name="userName">
				</div>
				<div class="flexME">
					<label>Password: </label>
					<input type="text" name="password">
				</div>
				<div class="flexME">
					<input type="submit" value="Login">
					<a href="registration.jsp">Registration</a>
				</div>
				<input type="hidden" name="pageFrom" value="login.jsp">
			</form>
		</div>
	</body>
</html>
<% bean.setMessage("");  %>