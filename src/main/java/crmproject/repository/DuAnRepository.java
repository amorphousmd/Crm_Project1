package crmproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crmproject.config.MysqlConfig;

public class DuAnRepository {
	public boolean insertAllFields(	String ten, String mota,
									String ngayBatDau, String ngayKetThuc, 
									int idNguoiQuanLy, int idTrangThai) {
		String query = "INSERT INTO DuAn(ten, mota, ngayBatDau, ngayKetThuc, id_nguoiquanly, id_trangthai)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, ten);
			statement.setString(2, mota);
			statement.setString(3, ngayBatDau);
			statement.setString(4, ngayKetThuc);
			statement.setInt(5, idNguoiQuanLy);
			statement.setInt(6, idTrangThai);
			
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
