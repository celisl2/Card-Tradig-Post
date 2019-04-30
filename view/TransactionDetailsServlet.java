package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
 * Servlet implementation class TransactionDetailsServlet
 */
@WebServlet("/TransactionDetailsServlet")
public class TransactionDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		SessionBean beanObj = (SessionBean) session.getAttribute("bean");
		
		
		String cameFrom = "";
		cameFrom = (String)request.getAttribute("origin");
		String cardName = (String) request.getAttribute("cardName");
		String cardNum = (String) request.getAttribute("cardNum");
		String action = (String) request.getAttribute("action");
		String price = (String) request.getAttribute("price");
		String quantity = (String) request.getAttribute("quantity");
		
		try {
			if(utilities.DataCheck.NullOrEmpty(cameFrom)) {
				String url = response.encodeRedirectURL("/login.jsp");
				response.sendRedirect(request.getContextPath() + url);
			}
			else {
				if(utilities.DataCheck.IfReferredBy(request, "cardTrader.jsp")) {
					//System.out.println(cardName + " " + cardNum + " " + action + " " + quantity + " " + price);
					
					if(action == "sell") {
						//check if the cards they currently have == quantity 
						if(cardNum.equals(quantity)) {
							beanObj.setCardsForSale(cardName, cardNum, price);
							beanObj.getCardCollection().remove(cardName);
						}
						else {
							beanObj.setCardsForSale(cardName, cardNum, price);
							Integer num = Integer.valueOf(cardNum);
							num--;
							cardNum = String.valueOf(num);
						}
					}
					beanObj.setTransactionDetails(cardName, cardNum, action, quantity, price);
					String url = response.encodeRedirectURL("/transactionDetails.jsp");
					response.sendRedirect(request.getContextPath() + url);
				}
				else {
					//confused :/
					String url = response.encodeRedirectURL("/cardTrader.jsp");
					response.sendRedirect(request.getContextPath() + url);
				}
			}
			
			
			
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
