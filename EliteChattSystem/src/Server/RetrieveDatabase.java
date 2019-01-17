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
	
	/**
	 * 
	 * @param username
	 * @param pass
	 * @return if the user exists in the databse or not
	 */
	public boolean checkIfUserInDatabase(String username, String pass){
		System.out.println("check login username " + username + " pass " + pass);
		String query = "SELECT * FROM users WHERE username ='" + username + "'AND password = '" + pass + "'";
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
