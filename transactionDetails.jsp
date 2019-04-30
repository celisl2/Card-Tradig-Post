<jsp:useBean id="bean" class="beans.SessionBean" scope="session"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Transaction Details</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css" type="text/css"/>
		<style>
		.account {
			display: flex;
			width: 80%;
			margin: auto;
		}
		</style>
	</head>
	<body>
		<h1>Transaction Details</h1>
		<% utilities.CreateTable.buildTransactionDetails(bean);%>
		<%= bean.getTableTransaction()%>
        
        <h1>Bank Account Information</h1>
        
        <form action="TransactionControllerServlet" method="POST">
        	<div class="acount">
        		<p>Account Holder Name: </p>
        		<input type="text" name="accName">
        	</div>
        	<div class="acount">
        		<p>Routing Number: </p>
        		<input type="text" name="routing">
        		<input type="hidden" name="pageFrom" value="transactionDetails.jsp">
        		<input type="submit" value="Confirm Transaction"/>
        	</div>
        </form>
        <form action="LogOutControllerServlet" style="border: none; margin: auto;"><input type="submit" value="Log out"/>
			</form>

	</body>
</html>