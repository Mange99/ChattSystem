package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author alejyb1
 *
 */
public class RetrieveDatabase {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	Statement stmt;
	ResultSet resultSet;
	ResultSet rs;
	
	/**
	 * 
	 * @param username
	 * @param pass
	 * @return if the user exists in the databse or not
	 */
	public boolean checkIfUserInDatabase(String username, String pass){
		System.out.println("check login username " + username + " pass " + pass);
		String query = "SELECT * FROM users WHERE username ='" + username + "'AND password = '" + pass + "'";
		String qry = "SELECT * FROM users";
		System.out.println(query);
		
		
		try {
			stmt = Database.conn.createStatement();
			rs = stmt.executeQuery(qry);
			while(rs.next()) {
				System.out.println("asidjhlsdfjhlsdfgjhklasdfjhlasdfjhklsdfjklsdfjkl");
				String un = rs.getString("username");
				String pw = rs.getString("password");
				
				System.out.println(un.toLowerCase().trim() +" "+ username.toLowerCase().trim() +" "+pw.toLowerCase().trim() +" " + pass.toLowerCase().trim());
				if(un.toLowerCase().trim().equals(username.toLowerCase().trim()) && pw.toLowerCase().trim().equals(pass.toLowerCase().trim())) {
					System.out.println("Den första returns true finns med i databasen");
					return true;
				}
				
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			stmt = Database.conn.createStatement();
			resultSet = stmt.executeQuery(query);
			
			if (resultSet.next()) {
				System.out.println("returns true finns med i databasen");
				return true;
			} else {
				System.out.println("returns false finns inte i databasen");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
