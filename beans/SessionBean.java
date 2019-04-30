package beans;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class SessionBean {
	
	private String userName;
	private String message;
	//private String cardCollection;
	//private String cardsForSale;
	private String password;
	//declares a new hash map called hashMapCards
	private HashMap<String, String[]> cardCollection = new HashMap<String, String[]>();
	private HashMap<String, String[]> cardsForSale = new HashMap<String, String[]>();
	private HashMap<String, String[]> transactionDetails = new HashMap<String, String[]>();
	private String tableCollection;
	private String tableForSale;
	private String tableTransaction;
	
	//add all stuff from card ie
	private String cardName;
	private String cardNum;
	private String action;
	private String price;
	private String quantity;
	private String numCardsOwned;
	private String userPrimaryKey;
	private String tableFrom;
	
	public SessionBean() {
		message = "";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<Entry<String, String[]>> getCardsForSale() {
		Set<Entry<String, String[]>> saleSet = cardsForSale.entrySet();
    	return saleSet;
	}

	public void setCardsForSale(String name, String cardNum, String cardCost) {
		this.cardsForSale.put(name, new String[] {cardNum, cardCost});
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setCardCollection(String cardName, String cardNum, String cardValue, String cardQuantity) {
		this.cardCollection.put(cardName, new String[] {cardNum, cardValue, cardQuantity});
	}
	/*
	 * add a deleteCard method and call remove passing in the cardName
	 */
	public void deleteCard(String cardName) {
		cardCollection.remove(cardName);
	}
	
	public Set<Entry<String, String[]>> getCardCollection() {
		Set<Entry<String, String[]>> cardSet = cardCollection.entrySet();
    	return cardSet;
	}

	public String getTableCollection() {
		return tableCollection;
	}

	public void setTableCollection(String tableCollection) {
		this.tableCollection = tableCollection;
	}

	public String getTableForSale() {
		return tableForSale;
	}

	public void setTableForSale(String tableForSale) {
		this.tableForSale = tableForSale;
	}

	public Set<Entry<String, String[]>> getTransactionDetails() {
		Set<Entry<String, String[]>> cardSet = transactionDetails.entrySet();
    	return cardSet;
	}

	public void setTransactionDetails(String cardName, String cardNum, String action, String qty, String price) {
		this.transactionDetails.put(cardName, new String[] {cardNum, action, qty, price});
	}

	public String getTableTransaction() {
		return tableTransaction;
	}

	public void setTableTransaction(String tableTransaction) {
		this.tableTransaction = tableTransaction;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getNumCardsOwned() {
		return numCardsOwned;
	}

	public void setNumCardsOwned(String numCardsOwned) {
		this.numCardsOwned = numCardsOwned;
	}

	public String getUserPrimaryKey() {
		return userPrimaryKey;
	}

	public void setUserPrimaryKey(String userPrimaryKey) {
		this.userPrimaryKey = userPrimaryKey;
	}

	public String getTableFrom() {
		return tableFrom;
	}

	public void setTableFrom(String tableFrom) {
		this.tableFrom = tableFrom;
	}

}
