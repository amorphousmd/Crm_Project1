package crmproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	// Find all projects with the specified user ID.
	public List<Integer> findAllIdWithUserId(int id) {
		String query = "SELECT * FROM congviec_nguoidung dn \r\n"
				+ "WHERE id_nguoidung = ?;";
		
		Connection connection = MysqlConfig.getConnection();
		List<Integer> listCongViecId = new ArrayList<Integer>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int projectId = resultSet.getInt("id_congviec");
				listCongViecId.add(projectId);
			}
			
		} catch (SQLException e) {
			System.out.println("Query execution error" + e.getLocalizedMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection." + e.getLocalizedMessage());
				}
			}
		}
		
		return listCongViecId;
	}
}
