package tri.dev.data.driver;

import java.sql.Connection;
import java.sql.DriverManager;

import static tri.dev.ulti.Constants.*;

//singleton
public class MySQLDriver {
	private static MySQLDriver instance; // instance dgl doi tuong

	private MySQLDriver() {

	}

	public static MySQLDriver getInstance() {
		if (instance == null)
			instance = new MySQLDriver();
		return instance;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}