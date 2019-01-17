package Server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasInsert {
	private PreparedStatement preparedStmt;

	public PreparedStatement getPreparedStmt(String query) {
		try {
			preparedStmt = Database.conn.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStmt;
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
			e.printStackTrace();
		}
	}
	
	public void insertNewPerson(String username, String password) {
		String query = "INSERT INTO users (username, password)" + "VALUES (?, ?)"; 
		getPreparedStmt(query);

		try {
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
