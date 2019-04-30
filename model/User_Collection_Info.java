package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.SessionBean;

public class User_Collection_Info {
	
	
	public static void getDBCardCollection(String userPrimK, SessionBean bean) {
		
		Connection con = Database_Connect.Connect2LocalDB();
		
		String cardName = "";
		String cardNumber = "";
		String cardValue = "";
		String amountOwned = "";
		
		
		try {
            PreparedStatement pass = con.prepareStatement("SELECT * FROM Card_Info WHERE userPrimaryKey = ?");
            pass.setString(1, userPrimK);
            ResultSet rs = pass.executeQuery();
            while (rs.next()){
            	cardName = rs.getString("cardName");
            	cardNumber = rs.getString("cardNumber");
            	cardValue = rs.getString("cardValue");
            	amountOwned = rs.getString("amountOwned");
            	
            	bean.setCardCollection(cardName, cardNumber, cardValue, amountOwned);
            	
            }

        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, User_Collection_Info.class.getName(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	public static void enterCard(String primKey, String cardName, String cardNum, String cardValue, String amountOwned) {
		Connection con = model.Database_Connect.Connect2LocalDB();
		
		try {
        	
            PreparedStatement prep = con.prepareStatement("INSERT INTO Card_Info (userPrimaryKey, cardName, cardNumber, cardValue, amountOwned)"
            		+ " VALUES (?,?,?,?,?)");
            
            prep.setString(1, primKey);
            prep.setString(2, cardName);
            prep.setString(3, cardNum);
            prep.setString(4, cardValue);
            prep.setString(5, amountOwned);
            prep.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, User_Collection_Info.class.getName() + ".enterCard ", ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	public static void updateCard(String primKey, String amountOwned, String cardNum) {
		Connection con = model.Database_Connect.Connect2LocalDB();
		
		try {
        	//UPDATE `Card_Info` SET `amountOwned`= 0 WHERE `userPrimaryKey` = 6 AND `cardNumber`= 482;
            PreparedStatement prep = con.prepareStatement("UPDATE Card_Info SET amountOwned = ? WHERE userPrimaryKey = ?"
            		+ "AND cardNumber = ?");
            
            prep.setString(1, amountOwned);
            prep.setString(2, primKey);
            prep.setString(3, cardNum);
            prep.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, User_Collection_Info.class.getName() + ".updateCard ", ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	public static String getCardName(String usrPrimKey) {

        Connection con = Database_Connect.Connect2LocalDB();

        String cardName = "";
        
        try {
            PreparedStatement pass = con.prepareStatement("SELECT cardName FROM Card_Info WHERE userPrimaryKey = ?");
            pass.setString(1, usrPrimKey);
            ResultSet resultPW = pass.executeQuery();
            resultPW.next();
            cardName = resultPW.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, User_Collection_Info.class.getName(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cardName;
    }
	
	public static String getCardNumber(String usrPrimKey) {

        Connection con = Database_Connect.Connect2LocalDB();

        String cardNum = "";
        
        try {
            PreparedStatement pass = con.prepareStatement("SELECT cardNumber FROM Card_Info WHERE userPrimaryKey = ?");
            pass.setString(1, usrPrimKey);
            ResultSet resultPW = pass.executeQuery();
            resultPW.next();
            cardNum = resultPW.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, User_Collection_Info.class.getName(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cardNum;
    }
	
	public static String getCardValue(String usrPrimKey) {

        Connection con = Database_Connect.Connect2LocalDB();

        String cardVal = "";
        
        try {
            PreparedStatement pass = con.prepareStatement("SELECT cardValue FROM Card_Info WHERE userPrimaryKey = ?");
            pass.setString(1, usrPrimKey);
            ResultSet resultPW = pass.executeQuery();
            resultPW.next();
            cardVal = resultPW.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, User_Collection_Info.class.getName(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cardVal;
    }
	
	public static String getAmountOwned(String usrPrimKey, String cardNum) {

        Connection con = Database_Connect.Connect2LocalDB();

        String amountOwned = "";
        
        try {
            PreparedStatement pass = con.prepareStatement("SELECT amountOwned FROM Card_Info WHERE userPrimaryKey = ? AND cardNumber = ?");
            pass.setString(1, usrPrimKey);
            pass.setString(2, cardNum);
            ResultSet resultPW = pass.executeQuery();
            resultPW.next();
            amountOwned = resultPW.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, User_Collection_Info.class.getName(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Collection_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amountOwned;
    } 

	public static boolean cardCheck(String usrPrimKey, String cardNum) {
		
		Connection con = model.Database_Connect.Connect2LocalDB();
		
		String dbCard = "";
        int cardCount = 0;
        try {

            PreparedStatement count = con.prepareStatement("SELECT cardNumber FROM Card_Info WHERE userPrimaryKey = ?"
            		+ "AND cardNumber = ?");
            count.setString(1, usrPrimKey);
            count.setString(2, cardNum);
            ResultSet rs = count.executeQuery();
            while (rs.next()){
            	dbCard = rs.getString("cardNumber");
					if (dbCard.equals(cardNum)){
						cardCount++;				
					}
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   "userNameCheck" + ex.getMessage(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (cardCount == 1) {
            return true;
        } else {
            return false;
        }
	}
}
