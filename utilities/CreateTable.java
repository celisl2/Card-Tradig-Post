package utilities;

import java.util.HashMap;

import beans.SessionBean;

public class CreateTable {
	 
	
	
	public static void buildCurrentCardCollection(SessionBean beanObject) {
		
		String cardName = "";
		String tableCollection = "";
		String table = "<div class=\"Table\">";
		
		tableCollection = "<table id=\"tableButton\"><tr><th>Card</th><th>Card Number</th><th>Value</th>"
        		+ "<th>Owned Amount</th><th>Total Value</th><th>Action</th><th>Quantity</th><th> </th></tr>";
		//table = "<div class=\"tableH\"><h4>Card</h4><h4>Card Number</h4><h4>Value</h4><h4>Owned Amount</h4><h4>Total Value</h4><h4>Action</h4><h4>Quantity</h4></div>";
		
		for (HashMap.Entry<String, String[]> entry : beanObject.getCardCollection()) {
			cardName = entry.getKey();
	        String cardInfoArr[] = entry.getValue();
	        
	        
	        table = table + "<div class=\"card\">"
	        					+ "<div class=\"tableR\">" + 
	        						"<div class=\"flexRow\"><h4>Card:</h4><p>" + cardName + "</p></div>" + 
	        						"<div class=\"flexRow\"><h4>Card Number:</h4><p>" + cardInfoArr[0] + "</p></div>" + 
	        						"<div class=\"flexRow\"><h4>Value:</h4><p>" + cardInfoArr[1] + "</p></div>" + 
	        						"<div class=\"flexRow\"><h4>Owned Amount:</h4><p>" + cardInfoArr[2] + "</p></div>" +
	        						"<div class=\"flexRow\"><h4>Total Value:</h4><p>" + Float.parseFloat(cardInfoArr[1]) *  Float.parseFloat(cardInfoArr[2]) + "</p></div>"
	        					+ "</div>" +
	        					"<form class=\"formTable\" action=\"TransactionControllerServlet\" method=\"POST\">"
	        					+ "<div class=\"flexRow\"><h4>Action: </h4>" 
	        						+ "<select name=\"action\">\n" + 
	        							"  <option value=\"buy\">Buy</option>\n" + 
	        							"  <option value=\"sell\">Sell</option>\n" + 
	        						"</select>"
	        					+ "</div>" + 
	        						"<div class=\"flexRow\"><h4>Quantity: </h4>" +
	        						"<input type=\"text\" name=\"quantity\"></div>" +
	        						"<input type=\"submit\" value=\"Make Transaction\">" +
	        					
	    	        				"<input type=\"hidden\" name=\"cardNum\" value=\"" + cardInfoArr[0] + "\">" +
	    	        				"<input type=\"hidden\" name=\"tableFrom\" value=\"collection\">"
	    	        				+ "<input type=\"hidden\" name=\"cardValue\" value=\"" + cardInfoArr[1] + "\">"
	    	        				+ "<input type=\"hidden\" name=\"amountOwned\" value=\"" + cardInfoArr[2] + "\">"
	    	        				+ "<input type=\"hidden\" name=\"cardName\" value=\"" + cardName + "\">"
	    	        				+ "<input type=\"hidden\" name=\"pageFrom\" value=\"cardTrader.jsp\">"
	    	        			+ "</form></div>"; 
	        
	    	        		
	    	        		
	        		
		}
		//tableCollection = tableCollection + "</table>";
		//beanObject.setTableCollection(tableCollection);
		table = table + "</div>";
		beanObject.setTableCollection(table);
	}
	
	public static void buildCardsForSale(SessionBean beanObject) {
		
		String cardName = "";
		String tableForSale = "";
		
		tableForSale = "<table><tr><th>Card</th><th>Card Number</th><th>Cost</th>"
        		+ "<th>Quantity</th><th> </th></tr>";
		
		for (HashMap.Entry<String, String[]> entry : beanObject.getCardsForSale()) {
			cardName = entry.getKey();
	        String cardInfoArr[] = entry.getValue();
	        
	        //System.out.println(cardName + " " + cardInfoArr[0] + " " + cardInfoArr[1]);
	        
	        tableForSale = tableForSale + "<tr><td>" + cardName + "</td><td>" + cardInfoArr[0] + "</td><td>" + cardInfoArr[1]
	        		+ "</td>" + "<td><form action=\"TransactionControllerServlet\" method=\"POST\">" +
	        		"<input type=\"hidden\" name=\"cardCost\" value=\"" + cardInfoArr[1] + "\">" +
	        		"<input type=\"hidden\" name=\"cardName\" value=\"" + cardName + "\">"
	        		+ "<input type=\"hidden\" name=\"cardNumber\" value=\"" + cardInfoArr[0] + "\">" +
	        		"<input type=\"hidden\" name=\"tableFrom\" value=\"availableCards\">"
	        		+ "<input type=\"hidden\" name=\"pageFrom\" value=\"cardTrader.jsp\">"
	        				+ "<input type=\"text\" name=\"quantityToBuy\"></td><td>"
	        		+ "<input type=\"submit\" value=\"Purchase\">"
	        		+ "</form></td></tr>";
		}
		tableForSale = tableForSale + "</table>";
		beanObject.setTableForSale(tableForSale);
	}

	public static void buildTransactionDetails(SessionBean beanObject) {
		String cardName = "";
		String transactionTable = "<table>";
		
		transactionTable = transactionTable + "<tr>\n" + 
				"            	<th>Card</th>\n" + 
				"            	<th>Number</th>\n" + 
				"            	<th>Buy / Sell</th>\n" + 
				"            	<th>Quantity</th>\n" + 
				"            	<th>Card Price</th>\n" + 
				"            	<th>Total Value</th>\n" + 
				"        	</tr>";
		
		for (HashMap.Entry<String, String[]> entry : beanObject.getTransactionDetails()) {
			cardName = entry.getKey();
	        String tDetailsArr[] = entry.getValue();
	        
	      System.out.println(cardName + " " + tDetailsArr[0] + " " + tDetailsArr[1] + " " + tDetailsArr[2] + " " + tDetailsArr[3]);
	        
	        
	        //"<tr>" "</tr>"
	        transactionTable = transactionTable + "<tr>"
	        					+ "<td>" + cardName + "</td>"
	        					+ "<td>" + tDetailsArr[0] + "</td>"
	        					+ "<td>" + tDetailsArr[1] + "</td>"
	        					+ "<td>" + tDetailsArr[2] + "</td>"
	        					+ "<td>" + tDetailsArr[3] + "</td>"
	        					+ "<td>" + Float.parseFloat(tDetailsArr[2]) *  Float.parseFloat(tDetailsArr[3]) + "</td>"
	        					+ "</tr>";
		}
		transactionTable = transactionTable + "</table>";
		beanObject.setTableTransaction(transactionTable);
	}
}
