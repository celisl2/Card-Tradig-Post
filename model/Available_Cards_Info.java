package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.SessionBean;

public class Available_Cards_Info {

	public static void getDBAvailableCards(SessionBean bean) {
		
		Connection con = Database_Connect.Connect2LocalDB();
		
		String cardName = "";
		String cardNumber = "";
		String cardValue = "";
		
		
		try {
            PreparedStatement pass = con.prepareStatement("SELECT * FROM Available_Cards");
            ResultSet rs = pass.executeQuery();
            while (rs.next()){
            	cardName = rs.getString("cardName");
            	cardNumber = rs.getString("cardNumber");
            	cardValue = rs.getString("cardCost");
            	
            	bean.setCardsForSale(cardName, cardNumber, cardValue);
            	
            }

        } catch (SQLException ex) {
            Logger.getLogger(Available_Cards_Info.class.getName()).log(Level.SEVERE, Available_Cards_Info.class.getName(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Available_Cards_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}
