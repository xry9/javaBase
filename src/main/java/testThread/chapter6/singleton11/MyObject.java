package testThread.chapter6.singleton11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum MyObject {
	connectionFactory;

	private Connection connection;

	private MyObject() {
		try {
//			String url = "jdbc:sqlserver://localhost:1079;databaseName=ghydb";
			String url = "jdbc:sqlserver://124.251.44.196:1433;DatabaseName=Esf_eb_user";
			String username = "esf_eb_user_bigdata_r";
			String password = "2bD5uhHU";
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return connection;
	}
}