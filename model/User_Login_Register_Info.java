package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User_Login_Register_Info {
	
	/***************************************************************************
	* 
	* some sql issues are w the fact you didnt use .next()
	* https://coderanch.com/t/494928/databases/java-sql-SQLException-start-result
	* 
	***************************************************************************/
	
	
	public static boolean userNameCheck(String userName) {
		
		Connection con = model.Database_Connect.Connect2LocalDB();
		
		String userNameDB = "";
        int userNameCount = 0;
        try {

            PreparedStatement count = con.prepareStatement("SELECT userName FROM User_Login_Register");
            ResultSet rs = count.executeQuery();
            while (rs.next()){
            	userNameDB = rs.getString("userName");
					if (userNameDB.equalsIgnoreCase(userName)){
						userNameCount = 1;					
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
        
        if (userNameCount == 0) {
            return true;
        } else {
            return false;
        }
	}
	
	public static boolean userN_passCheck(String userName, String userPassword) {
		
		Connection con = model.Database_Connect.Connect2LocalDB();
		
		String passwReturned = "";
		int passCount = 0;
		System.out.println("IN METHOD");
        try {

            PreparedStatement count = con.prepareStatement("SELECT userPassword FROM User_Login_Register WHERE userName = ?");
            count.setString(1, userName);
            ResultSet rs = count.executeQuery();
            while (rs.next()){
            	passwReturned = rs.getString("userPassword");
					if (passwReturned.equalsIgnoreCase(userPassword)){
						passCount = passCount + 1;			
					}
					else
						passCount = 0;
            }
            //passwReturned = rs.getString("userPassword");
 
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() +   ".userN_passCheck" + ex.getMessage(), ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(passCount == 1) {
        	System.out.println("returned true in user-login");
            return true;
        } else {
        	System.out.println("returned false in user-login");
            return false;
        }
	}
	
	public static Integer getUserPrimaryKey(String userName) {
		
		Connection con = model.Database_Connect.Connect2LocalDB();
        
        int userKey = 0;

        try {
            PreparedStatement key = con.prepareStatement("SELECT userPrimaryKey FROM User_Login_Register WHERE userName = ?");
            key.setString(1, userName);
            ResultSet resultUserKey = key.executeQuery();
            
            resultUserKey.next();
            userKey = resultUserKey.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName()+ ".getUserPrimaryKey ", ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(" FROM USER_REG primary key is " + userKey + " ********************************************");
        return userKey;
	}
	
	public static void setNewUser_Pass(String userName, String userPassword) {
		
		Connection con = model.Database_Connect.Connect2LocalDB();
		try {
        	
            PreparedStatement prep = con.prepareStatement("INSERT INTO User_Login_Register (userName, userPassword) VALUES (?,?)");
            //inserts user car (question mark # 1) into our query
            prep.setString(1, userName);
            prep.setString(2, userPassword);
            prep.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, User_Login_Register_Info.class.getName() + ".setNewUser_Pass ", ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User_Login_Register_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
}
