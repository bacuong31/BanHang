package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {
	
	private Connection conn = null;
	private static DatabaseManager instance = null;
	String url = "jdbc:sqlite:Database.db";
	
	public DatabaseManager() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(url);
	}
	
	public static DatabaseManager getInstance() throws SQLException, ClassNotFoundException{
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}
}