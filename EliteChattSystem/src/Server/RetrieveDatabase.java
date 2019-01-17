package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetrieveDatabase {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static Connection conn;
	Statement stmt;
	ResultSet resultSet;
	
	public RetrieveDatabase() {
		conn = Database.getConnectionDatabase("jdbc:mysql://localhost:3306/ChattSystem?autoReconnect=true&useSSL=false", "root", "");
		System.out.println("constructor " + conn);
	}

	public boolean checkLogin(String username, String pass){
		String query = "SELECT * FROM users WHERE username ='" + username + "'AND password = '" + pass + "'";
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (resultSet.next()) {
				System.out.println("returns true");
				return true;
			} else {
				System.out.println("returns false");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
