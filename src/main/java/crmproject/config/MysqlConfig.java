package crmproject.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConfig {
	public static Connection getConnection() {
		Connection connection = null;
		// Khai bao drive su dung cho CSDL
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", "root", "123456");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Database connection error" + e.getLocalizedMessage());
		}
		return connection;
	}
}
