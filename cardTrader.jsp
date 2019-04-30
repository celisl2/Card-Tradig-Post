<%@page import="constants.htmlConstants"%>
<jsp:useBean id="bean" class="beans.SessionBean" scope="session"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Card Trader</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css" type="text/css"/>
	</head>
	<body>
		<div class="center">
			<h1>Current Card Collection for <%= bean.getUserName() %></h1>
			<center><p style="color: red"><%= bean.getMessage()  %></p></center>
			<%
				utilities.CreateTable.buildCurrentCardCollection(bean);
				utilities.CreateTable.buildCardsForSale(bean);
			%>
			<style>
				.flexRow {
					display: flex;
					align-items: center;
					align-content: space-between;
					flex-wrap: nowrap;
				}
				.flexRow h4 {
					padding-right: 3px;
				}
				.flexRow p {
					flex-basis: auto;
				}
				.card {
					display: flex;
					width: 80%;
					margin: auto;
					padding: 1%;
					border-left: solid 4px #3c556b;
					border-right: solid 4px #3c556b;
					
				}
				.card:nth-child(odd) {
					background-color: #d4d4d4;
				}
				.Table {
					width: 100%;
					margin: auto;
				}
			
			
			</style>
			<%= bean.getTableCollection() %>
			<h1> Available Cards to Buy </h1>
			<%= bean.getTableForSale()%>
			
			<form action="LogOutControllerServlet" style="border: none; margin: auto;"><input type="submit" value="Log out"/>
			</form>
		</div>
	</body>
</html>
<% bean.setMessage("");  %>