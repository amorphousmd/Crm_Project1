package crmproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crmproject.config.MysqlConfig;

public class CongViecNguoiDungRepository {
	public boolean insertAllFields(int id_congviec, int id_nguoidung) {
		String query = "INSERT INTO congviec_nguoidung(id_congviec, id_nguoidung) \r\n"
				+ "VALUES (?, ?);";
		
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id_congviec);
			statement.setInt(2, id_nguoidung);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Query execution error" + e.getLocalizedMessage());
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Close connection failed" + e.getLocalizedMessage());
				}
			}
		}
		return true;
	}
}
