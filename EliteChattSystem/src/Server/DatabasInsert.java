package Server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasInsert {
	private PreparedStatement preparedStmt;

	public DatabasInsert() {
		
	}

	public PreparedStatement getPreparedStmt(String query) {
		try {
			preparedStmt = Database.conn.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("halleluja");
		return preparedStmt;
	}
	
	public void createSuperAdmin() {
		String query = "INSERT INTO users (username, password)" + "VALUES (?, ?)";
		getPreparedStmt(query);
		
		String username = "";
		String password = "";
		
		try {
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertMessage(String message, String username, String time) {
		String query = "INSERT INTO messages (message, username, time)" + "VALUES (?, ?, ?)";
		getPreparedStmt(query);

		try {
			preparedStmt.setString(1, message);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, time);
			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertPerson(String username, String password) {
		String query = "INSERT INTO users (username , password)" + "VALUES (?, ?)";
		getPreparedStmt(query);

		try {
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
