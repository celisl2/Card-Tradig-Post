package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SessionBean;

/**
 * Servlet implementation class RegistrationControllerServlet
 */
@WebServlet("/RegistrationControllerServlet")
public class RegistrationControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		String url = response.encodeRedirectURL("/CardTradingServlet");
		response.sendRedirect(request.getContextPath() + url); */
		HttpSession session = request.getSession(false);
		SessionBean beanObj = (SessionBean) session.getAttribute("bean");
		String usrName = request.getParameter("userName");
		String pw = request.getParameter("password");
		String pwConfirm = request.getParameter("passwordConfirm");
		String invalidName = "";
		String pageCameFrom = request.getParameter("pageFrom");
		
		/*check what they provided was not null or empty
		 * 	if so send them back to login w the message
		 * 
		 * 
		 */
		
		if(utilities.DataCheck.NullOrEmpty(usrName)) {
			invalidName = "Do not leave empty";
			beanObj.setMessage(invalidName);
			String url = response.encodeRedirectURL("/registration.jsp");
			response.sendRedirect(request.getContextPath() + url);
		}
		else if(!pw.equals(pwConfirm)) {
			invalidName = "Passwords do not match";
			beanObj.setMessage(invalidName);
			String url = response.encodeRedirectURL("/registration.jsp");
			response.sendRedirect(request.getContextPath() + url);
		}
		else {
			if(model.User_Login_Register_Info.userNameCheck(usrName)) {
				//username available
				
				model.User_Login_Register_Info.setNewUser_Pass(usrName, pw);
				String usrPrimaryK = model.User_Login_Register_Info.getUserPrimaryKey(usrName).toString();
				
				model.User_Collection_Info.enterCard(usrPrimaryK, "1983 Topps Tony Gwynn RC", "482", "75.21", "1");
				model.User_Collection_Info.enterCard(usrPrimaryK, "1984 Fleer Update Roger Clemens", "27", "120.00", "2");
				model.User_Collection_Info.enterCard(usrPrimaryK, "1983 Topps Ryne Sandberg", "83", "20.00", "3");
				
				beanObj.setUserPrimaryKey(usrPrimaryK);
				beanObj.setUserName(usrName);
				
				String url = response.encodeRedirectURL("/CardTradingControllerServlet");
				response.sendRedirect(request.getContextPath() + url);
				
			}
			else {
				beanObj.setMessage( usrName + " already taken. Please choose another one");
				String url = response.encodeRedirectURL("/registration.jsp");
				response.sendRedirect(request.getContextPath() + url);
			}
		}
		
		/*
		if((usrName.equals("bob")) || (usrName.equals("peg")) || (usrName.equals("pat")) || (usrName.isEmpty())) {
			
			if(usrName.isEmpty())
				invalidName = "Do not leave empty";
			else
				invalidName = usrName + " taken. Chose another username";
			beanObj.setMessage(invalidName);
			String url = response.encodeRedirectURL("/registration.jsp");
			response.sendRedirect(request.getContextPath() + url);
		}
		else {
			if(pw.equals(pwConfirm)) {
				beanObj.setUserName(usrName);
setFreeCards(beanObj);
				request.setAttribute("origin", pageCameFrom);
				RequestDispatcher rd = request.getRequestDispatcher("/CardTradingServlet");
				rd.forward(request,response);
				
			}
			else {
				invalidName = "Passwords do not match";
				beanObj.setMessage(invalidName);
				String url = response.encodeRedirectURL("/registration.jsp");
				response.sendRedirect(request.getContextPath() + url);
			}
		}
	*/}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
