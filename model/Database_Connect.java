package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 	*************************8 HW FILE *************************
 */
public class Database_Connect {
	
	public static String user = "try2", pass = "try2";
	
	 public static Connection Connect2LocalDB() {

	        Connection con = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/CardTrader", Database_Connect.user, Database_Connect.pass);

	            return con;
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2LocalDB" + " ClassNotFoundException", ex);
	        } catch (SQLException ex) {
	            Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2LocalDB" + " SQLException", ex);
	        }
	        //checks if connection is not there then do the other one
	        if (con == null){
	        	con = Connect2RemoteDB();
	        }
	        return con;

	    }   

	    public static Connection Connect2RemoteDB() {
	    	System.out.println(Database_Connect.class.getName() + ".Connecting to remote DB");    	
	    	Connection con = null;
	    	/*
	    	 * to debug for sql errors
	    	 * cd ../../../../ go to cd /etc/mysql
	    	 * cd mysql.conf.d/
	    	 * sudo nano mysqld.cnf
	    	 * go to  'bind address' and comment it out(add # before text) + save it + exit
	    	 * restart the server (on google compute)to get this to work
	    	 */
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            // https://medium.com/modernnerd-code/connecting-to-mysql-db-on-aws-ec2-with-jdbc-for-java-91dba3003abb#.6adz9lk1h
	            //conncetions to a mysql database run on port 2206
	            con = DriverManager.getConnection("jdbc:mysql://35.186.191.11:3306/CardTrader", Database_Connect.user, Database_Connect.pass);
	            System.out.println(Database_Connect.class.getName() + ".Connection to remote DB successful");
	            return con;
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2RemoteDB" + " ClassNotFoundException", ex);
	        } catch (SQLException ex) {
	            Logger.getLogger(Database_Connect.class.getName()).log(Level.SEVERE, Database_Connect.class.getName() + ".Connect2RemoteDB" + " SQLException", ex);
	        }
	        return con;
	    }


}
