package Server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasInsert {
	private PreparedStatement preparedStmt;

	public DatabasInsert() {
		//Creates a login for testing purposes
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

	public PreparedStatement getPreparedStmt(String query) {
		System.out.println("databasinsert kör prepstmt");
		try {
			preparedStmt = Database.conn.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStmt;
	}
	
	public void createSuperAdmin() {
	
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

	public void insertPerson(String insertQuery) {
		String[] namePass = insertQuery.substring(9).split(":");
		String query = "INSERT INTO users (username, password)" + "VALUES (?, ?)"; 
		getPreparedStmt(query);

		try {
			preparedStmt.setString(1, namePass[0]);
			preparedStmt.setString(2, namePass[1]);
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
