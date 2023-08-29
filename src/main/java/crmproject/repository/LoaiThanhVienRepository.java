package crmproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmproject.config.MysqlConfig;
import crmproject.entity.LoaiThanhVien;

public class LoaiThanhVienRepository {
	public boolean insertNameAndDescription(String rolename, String description) {
		String query = "INSERT INTO LoaiThanhVien(ten, mota)\r\n"
				+ "VALUES (?, ?);";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			// Truyen query vao trong connection
			PreparedStatement statement = connection.prepareStatement(query);
			// Truyen gia tri tham so vao query neu co (Cho nao co ? la co tham so)
			statement.setString(1, rolename); // setInt, setString phai phu thuoc vao kieu du lieu cua cot dang so sanh tham so
			statement.setString(2, description);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Query execution error" + e.getLocalizedMessage());
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Loi dong ket noi" + e.getLocalizedMessage());
				}
			}
		}
		return true;
	}
	
	public List<LoaiThanhVien> findAll() {
		String query = "SELECT *\r\n"
				+ "FROM loaithanhvien l;";
		
		Connection connection = MysqlConfig.getConnection();
		List<LoaiThanhVien> listLoaiThanhVien = new ArrayList<LoaiThanhVien>();
		
		try {
			// Truyen query vao trong connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setId(resultSet.getInt("id"));
				loaiThanhVien.setTen(resultSet.getString("ten"));
				loaiThanhVien.setMota(resultSet.getString("mota"));
				
				listLoaiThanhVien.add(loaiThanhVien);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Query execution error" + e.getLocalizedMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Loi dong ket noi" + e.getLocalizedMessage());
				}
			}
		}
		
		return listLoaiThanhVien;
	}
	
	public int delelteById(int id) {
		int count = 0;
		String query = "DELETE FROM loaithanhvien WHERE id = ?";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
}
