package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SessionBean;

/**
 * Servlet implementation class CardTradingServlet
 */
@WebServlet("/CardTradingServlet")
public class CardTradingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String htmlStart = constants.htmlConstants.htmlHead("Card Trading Servlet");
		String title1 = constants.htmlConstants.headers("Current Card Collection");
		String title2 = constants.htmlConstants.headers("Available Cards to Buy");
		String logOut = constants.htmlConstants.logOff;
		String closeHtml = constants.htmlConstants.closeHtml;
		
		HttpSession session = request.getSession(false);
		SessionBean beanObj = (SessionBean) session.getAttribute("bean");
		
		//check where the request came from
		//if(HttpServletRequest.getHeader())
		//System.out.println("in cardTrading" + request.getHeader("Referer"));
		String pageFrom = (String)request.getAttribute("origin");
		//System.out.println(pageFrom);
		try {
			if( (pageFrom.equals("registration.jsp")) || (pageFrom.equals("login.jsp"))) {
				beanObj.setCardCollection("1983 Topps Tony Gwynn RC", "482", "75.21", "1");
				beanObj.setCardCollection("1984 Fleer Update Roger Clemens", "27", "120.00", "2");
				beanObj.setCardCollection("1983 Topps Ryne Sandberg", "83", "20.00", "3");
			
				beanObj.setCardsForSale("1984 Donruss Don Mattingly", "248", "40");
				beanObj.setCardsForSale("1984 Donruss Joe Carter RC", "41", "8");
				beanObj.setCardsForSale("1984 Donruss Darryl Strawberry", "68", "12");
			}
			else {
				String url = response.encodeRedirectURL("/login.jsp");
				response.sendRedirect(request.getContextPath() + url);
			}
			
			/*
			utilities.CreateTable.buildCurrentCardCollection(beanObj);
			utilities.CreateTable.buildCardsForSale(beanObj);
			out.print(htmlStart + title1 + beanObj.getTableCollection() + title2 + beanObj.getTableForSale() + logOut + closeHtml);
			*/
			String url = response.encodeRedirectURL("/cardTrader.jsp");
			response.sendRedirect(request.getContextPath() + url);
		}
		finally {
			out.close();
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
