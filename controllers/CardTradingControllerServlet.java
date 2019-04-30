package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SessionBean;

/**
 * Servlet implementation class CardTradingControllerServlet
 */
@WebServlet("/CardTradingControllerServlet")
public class CardTradingControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		SessionBean beanObj = (SessionBean) session.getAttribute("bean");
		String userN = beanObj.getUserName();
		//String primaryKeyUser = model.User_Login_Register_Info.getUserPrimaryKey(userN).toString();
		
		if(utilities.DataCheck.NullOrEmpty(userN)) {
			String url = response.encodeRedirectURL("/login.jsp");
			response.sendRedirect(request.getContextPath() + url);
		}
		else {
			model.User_Collection_Info.getDBCardCollection(beanObj.getUserPrimaryKey(), beanObj);
			model.Available_Cards_Info.getDBAvailableCards(beanObj);
			
			String url = response.encodeRedirectURL("/cardTrader.jsp");
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
