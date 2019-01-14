package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static Connection conn;
	public static Statement statement;

	private static String databaseName = "ChattSystem";
	private static String useSSL = "?autoReconnect=true&useSSL=false";
	private static String userName = "root";
	private static String password = "";
	private static String url = "jdbc:mysql://localhost:3306/";
	private DatabasInsert insert;
	public Database() throws InstantiationException, IllegalAccessException {
		conn = null;
		createDatabase();
	
		try {
			createUserTable();
			createMessagesTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insert = new DatabasInsert();
		insert.createSuperAdmin();
	}

	public static Connection getConnectionDatabase(String url, String userName, String password) {
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void createDatabase() {
		try {

			Class.forName(DRIVER);
			conn = getConnectionDatabase(url + useSSL, userName, password);

			String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;

			statement = conn.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			System.out.println(databaseName + " Database has been created successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Creates the messagestable
	public static void createMessagesTable() {
		String messagesTable = "CREATE TABLE IF NOT EXISTS messages ("
				+ "id_pk INT(64) NOT NULL AUTO_INCREMENT,"
				+ "message VARCHAR(255),"
				+ "user VARCHAR(255),"
				+ "PRIMARY KEY(id_pk))";
	}
	
	// Create the usertable
	public static void createUserTable() throws Exception {
		String userTable = "CREATE TABLE IF NOT EXISTS users (" 
				+ "id_pk INT(64) NOT NULL AUTO_INCREMENT,"
				+ "username VARCHAR(255), "
				+ "password VARCHAR(255),"
				+ "PRIMARY KEY(id_pk))";
		try {
			Class.forName(DRIVER);
			conn = getConnectionDatabase(url + databaseName + useSSL, userName, password);
			statement = conn.createStatement();
			statement.executeUpdate(userTable);
			System.out.println("Table Created");
		} catch (SQLException e) {
			System.out.println("An error has occured on Table Creation");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("An Mysql drivers were not found");
		}

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Couldn’t load database driver: " + cnfe.getMessage());
		}
	}
}
