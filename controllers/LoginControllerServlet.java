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
import model.User_Login_Register_Info;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		String url = response.encodeRedirectURL("/CardTradingServlet");
		response.sendRedirect(request.getContextPath() + url);
		---- other way to redirect ---
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/CardTradingServlet");
		rd.forward(request, response); */
		
		//obtaining session bean object
		//getSession(false) will return the current session and will not create new one if it already exists
		HttpSession session = request.getSession(false);
		//why does this need to be casted - does it return a string
		SessionBean beanObj = (SessionBean) session.getAttribute("bean");
		
		System.out.println(" in log in");
		
		String usrName = request.getParameter("userName");
		String pw = request.getParameter("password");
		String pageCameFrom = request.getParameter("pageFrom");
		
		
		
			if(model.User_Login_Register_Info.userN_passCheck(usrName, pw)) {
				System.out.println("USERNAME IN DB SUCCESS ********************");
				
				beanObj.setUserName(usrName);
				Integer primaryKey = model.User_Login_Register_Info.getUserPrimaryKey(usrName);
				System.out.println(" from log in primary key is: " + primaryKey + "***************************************************");
				beanObj.setUserPrimaryKey(String.valueOf(primaryKey));
			
				request.setAttribute("origin", pageCameFrom);
				RequestDispatcher rd = request.getRequestDispatcher("/CardTradingControllerServlet");
				rd.forward(request,response);
		}
		else {
			String invalidLogIn = "*** Invalid login, try again ***";
			beanObj.setMessage(invalidLogIn);
			String url = response.encodeRedirectURL("/login.jsp");
			response.sendRedirect(request.getContextPath() + url);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
