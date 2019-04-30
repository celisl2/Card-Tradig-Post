package controllers;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class TransactionControllerServlet
 */
@WebServlet("/TransactionControllerServlet")
public class TransactionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * String url = response.encodeRedirectURL("/CardTradingServlet");
			response.sendRedirect(request.getContextPath() + url);
		 */
		
		HttpSession session = request.getSession(false);
		SessionBean beanObj = (SessionBean) session.getAttribute("bean");
		
		String cardName = (String)request.getParameter("cardName");
		String cardNum = (String)request.getParameter("cardNum");
		String amountOwned = (String)request.getParameter("amountOwned");
		String action = (String)request.getParameter("action");
		String cardValue = (String)request.getParameter("cardValue");
		String quantity = (String)request.getParameter("quantity");
		String pageCameFrom = (String)request.getParameter("pageFrom");
		String tableFrom = (String)request.getParameter("tableFrom");
		
		//System.out.println(pageCameFrom);
		
		if(pageCameFrom == null) {
			String url = response.encodeRedirectURL("/login.jsp");
			response.sendRedirect(request.getContextPath() + url);
		}
		else {
			if( (pageCameFrom.equals("cardTrader.jsp")) || (pageCameFrom.equals("transactionDetails.jsp")) ) {
				
				if(pageCameFrom.equals("cardTrader.jsp")) {
					
					
					if(!tableFrom.equals("availableCards")) {
						
						int quantityNum = 0;
						if(!utilities.DataCheck.NullOrEmpty(quantity))
							quantityNum = Integer.parseInt(quantity);
						int amountOwnedNum = Integer.parseInt(amountOwned);
						
						if( (utilities.DataCheck.NullOrEmpty(action)) || (utilities.DataCheck.NullOrEmpty(quantity))) {
							
							
							String invalidInput = "*** Do not leave fields empty ***";
							beanObj.setMessage(invalidInput);
							
							String url = response.encodeRedirectURL("/cardTrader.jsp");
							response.sendRedirect(request.getContextPath() + url);
						}
						else if((action.equals("sell")) && (amountOwnedNum < quantityNum)) {
							String invalidInput = "*** Sorry, you can't sell more than you own ***";
							beanObj.setMessage(invalidInput);
							
							String url = response.encodeRedirectURL("/cardTrader.jsp");
							response.sendRedirect(request.getContextPath() + url);
						}
						else if (quantityNum <= 0) {
							String invalidInput = "*** Sorry, quantity must be a positve number and greater than 1 ***";
							beanObj.setMessage(invalidInput);
							
							String url = response.encodeRedirectURL("/cardTrader.jsp");
							response.sendRedirect(request.getContextPath() + url);
						}
						else if((action.equals("buy")) && (amountOwnedNum < quantityNum)) {
							String invalidInput = "*** Sorry, quantity must be less than amount owned ***";
							beanObj.setMessage(invalidInput);
							
							String url = response.encodeRedirectURL("/cardTrader.jsp");
							response.sendRedirect(request.getContextPath() + url);
						}
						else {
							beanObj.setCardName(cardName);
						beanObj.setCardNum(cardNum);
						beanObj.setAction(action);
						beanObj.setPrice(cardValue);
						beanObj.setQuantity(quantity);
						beanObj.setNumCardsOwned(amountOwned);
						request.setAttribute("origin", "cardTrader.jsp");
						
						beanObj.setTransactionDetails(cardName, cardNum, action, quantity, cardValue);
						beanObj.setTableFrom("transDetails");
						
						String url = response.encodeRedirectURL("/transactionDetails.jsp");
						response.sendRedirect(request.getContextPath() + url);
						}
						
						
					}
					else {
						if(tableFrom.equals("availableCards")) {
							
							
							String cardNameAvialble = (String)request.getParameter("cardName");
							String cardNumAvialble = (String)request.getParameter("cardNumber");
							String cardCost = (String)request.getParameter("cardCost");
							String quantityToBuy = (String)request.getParameter("quantityToBuy");

							System.out.println(quantityToBuy);
							
							beanObj.setAction("buy");
							beanObj.setCardName(cardNameAvialble);
							beanObj.setCardNum(cardNumAvialble);
							beanObj.setPrice(cardCost);
							beanObj.setNumCardsOwned(quantityToBuy);
							beanObj.setTableFrom(tableFrom);
							beanObj.setQuantity(quantityToBuy);
							request.setAttribute("origin", "cardTrader.jsp");
							
							beanObj.setTransactionDetails(cardNameAvialble, cardNumAvialble, beanObj.getAction(), quantityToBuy, cardCost);
							
							String url = response.encodeRedirectURL("/transactionDetails.jsp");
							response.sendRedirect(request.getContextPath() + url);
							
						}
					}
					
				}
				else {
					if(pageCameFrom.equals("transactionDetails.jsp")) {
					
						
						
						int quantityNum = 0;
						int amountOwnedNum = 0;
						if(!beanObj.getTableFrom().equals("availableCards")) {
							 quantityNum = Integer.parseInt(beanObj.getQuantity());
							amountOwnedNum = Integer.parseInt(beanObj.getNumCardsOwned());
						}
						
						String userPrimaryK = beanObj.getUserPrimaryKey();
						String cardN = beanObj.getCardNum();
						
						if(beanObj.getAction().equals("sell")) {
							if(quantityNum == amountOwnedNum) {
								
								model.User_Collection_Info.updateCard(userPrimaryK, "0", cardN);
								
							}
							else {
								
								String newAmount = String.valueOf(amountOwnedNum - quantityNum);
								
								model.User_Collection_Info.updateCard(userPrimaryK, newAmount, cardN);
								
								beanObj.setCardCollection(beanObj.getCardName(), beanObj.getCardNum(), 
										beanObj.getPrice(), newAmount);
							}
							String url = response.encodeRedirectURL("/cardTrader.jsp");
							response.sendRedirect(request.getContextPath() + url);
							
						}
						else {
							//they are buying
							if(beanObj.getTableFrom().equals("availableCards")) {
								
								
								if(model.User_Collection_Info.cardCheck(userPrimaryK, beanObj.getCardNum())) {
									
									quantityNum = Integer.parseInt(beanObj.getQuantity());
									Integer num = Integer.parseInt(model.User_Collection_Info.getAmountOwned(userPrimaryK, cardN));
									String newAmount = String.valueOf(num + quantityNum);
									model.User_Collection_Info.updateCard(userPrimaryK, newAmount, cardN);
									
									beanObj.setCardCollection(beanObj.getCardName(), beanObj.getCardNum(), 
											beanObj.getPrice(), newAmount);
								}
								else {
									model.User_Collection_Info.enterCard(userPrimaryK, beanObj.getCardName(),
										beanObj.getCardNum(), beanObj.getPrice(), beanObj.getNumCardsOwned());
									
									beanObj.setCardCollection(beanObj.getCardName(), beanObj.getCardNum(), 
											beanObj.getPrice(), beanObj.getNumCardsOwned());
								}
								
							}
							else {
								
								String newAmount = String.valueOf(amountOwnedNum + quantityNum);
								
								model.User_Collection_Info.updateCard(userPrimaryK, newAmount, cardN);
								
								beanObj.setCardCollection(beanObj.getCardName(), beanObj.getCardNum(), 
										beanObj.getPrice(), newAmount);
								
								
							}
							
							
							String url = response.encodeRedirectURL("/cardTrader.jsp");
							response.sendRedirect(request.getContextPath() + url); 
							
						}
						
					}
					else {
						String url = response.encodeRedirectURL("/login.jsp");
						response.sendRedirect(request.getContextPath() + url);
					}
					
				}
			}
			else 
			{
				String url = response.encodeRedirectURL("/login.jsp");
				response.sendRedirect(request.getContextPath() + url);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
