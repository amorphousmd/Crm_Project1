package crmproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmproject.config.MysqlConfig;

public class DuAnNguoiDungRepository {
	public boolean insertAllFields(int id_duan, int id_nguoidung) {
		String query = "INSERT INTO duan_nguoidung (id_duan, id_nguoidung)\r\n"
				+ "VALUES (?, ?);";
		
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id_duan);
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
		String query = "SELECT * FROM duan_nguoidung dn \r\n"
				+ "WHERE id_nguoidung = ?;";
		
		Connection connection = MysqlConfig.getConnection();
		List<Integer> listDuAnId = new ArrayList<Integer>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int projectId = resultSet.getInt("id_duan");
				listDuAnId.add(projectId);
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
		
		return listDuAnId;
	}
	
	// Delete a row from table, specified by the ID.
	public int deleteAtIds(int id_duan, int id_nguoidung) {
		int count = 0;
		String query = "DELETE FROM duan_nguoidung \r\n"
				+ "WHERE id_duan = ? AND id_nguoidung = ?;";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id_duan);
			statement.setInt(2, id_nguoidung);
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
}
