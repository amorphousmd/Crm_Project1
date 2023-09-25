package crmproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmproject.config.MysqlConfig;
import crmproject.entity.CongViec;
import crmproject.entity.DuAn;
import crmproject.entity.TrangThai;

public class CongViecRepository {
	// Get all task entries.
	public List<CongViec> findAll() {
		String query = "SELECT *\r\n"
				+ "FROM congviec c;";
		Connection connection = MysqlConfig.getConnection();
		List<CongViec> listCongViec = new ArrayList<CongViec>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				CongViec congViec = new CongViec();
				congViec.setId(resultSet.getInt("id"));
				congViec.setTen(resultSet.getString("ten"));
				congViec.setMota(resultSet.getString("mota"));
				congViec.setNgayBatDau(resultSet.getString("ngayBatDau"));
				congViec.setNgayKetThuc(resultSet.getString("ngayKetThuc"));
				DuAn duAn = new DuAn();
				duAn.setId(resultSet.getInt("id_duan"));
				congViec.setDuAn(duAn);
				TrangThai trangThai = new TrangThai();
				trangThai.setId(resultSet.getInt("id_trangthai"));
				congViec.setTrangThai(trangThai);
				listCongViec.add(congViec);
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
		
		return listCongViec;
	}
	
	public CongViec findAtId(int id) {
		CongViec congViec = new CongViec();
		
		String query = "SELECT * FROM congviec c \r\n"
				+ "WHERE id = ?;";
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				congViec.setId(resultSet.getInt("id"));
				congViec.setTen(resultSet.getString("ten"));
				congViec.setMota(resultSet.getString("mota"));
				congViec.setNgayBatDau(resultSet.getString("ngayBatDau"));
				congViec.setNgayKetThuc(resultSet.getString("ngayKetThuc"));
				DuAn duAn = new DuAn();
				duAn.setId(resultSet.getInt("id_duan"));
				congViec.setDuAn(duAn);
				TrangThai trangThai = new TrangThai();
				trangThai.setId(resultSet.getInt("id_trangthai"));
				congViec.setTrangThai(trangThai);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return congViec;
	}
	
	// Delete a row from table, specified by the ID.
	public int deleteAtId(int id) {
		int count = 0;
		String query = "DELETE FROM congviec WHERE id = ?;";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
		
		
	public int modifyAtId(	int id, String ten,
							String mota, String ngayBatDau, 
							String ngayKetThuc, int id_duan, 
							int id_trangthai) {
		
		int count = 0;
		String query = "UPDATE congviec  \r\n"
				+ "SET ten=?, mota=?, ngayBatDau=?, ngayKetThuc=?,id_duan=?,id_trangthai=? \r\n"
				+ "WHERE id=?;";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			mota = "editLater";
			statement.setString(1, ten);
			statement.setString(2, mota);
			statement.setString(3, ngayBatDau);
			statement.setString(4, ngayKetThuc);
			statement.setInt(5, id_duan);
			statement.setInt(6, id_trangthai);
			statement.setInt(7, id);
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	// Insert all possible field in a entry.
	public boolean insertAllFields(	String ten, String mota,
									String ngayBatDau, String ngayKetThuc, 
									int idDuAn) {
		String query = "INSERT INTO congviec(ten, mota, ngayBatDau, ngayKetThuc, id_duan, id_trangthai) \r\n"
				+ "VALUES (?, ?, ?, ?, ?, 3)";
		
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, ten);
			statement.setString(2, mota);
			statement.setString(3, ngayBatDau);
			statement.setString(4, ngayKetThuc);
			statement.setInt(5, idDuAn);
			
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
	
	public int findLatestId() {
		String query = "SELECT MAX(id) FROM congviec c ;";
		Connection connection = MysqlConfig.getConnection();
		
		int maxId = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				maxId = resultSet.getInt(1);
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
		
		return maxId;
	}
	
	public List<CongViec> findUnderProject(int projectID) {
		String query = "SELECT * FROM congviec c \r\n"
				+ "WHERE id_duan = ?";
		Connection connection = MysqlConfig.getConnection();
		List<CongViec> listCongViec = new ArrayList<CongViec>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, projectID);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				CongViec congViec = new CongViec();
				congViec.setId(resultSet.getInt("id"));
				congViec.setTen(resultSet.getString("ten"));
				congViec.setMota(resultSet.getString("mota"));
				congViec.setNgayBatDau(resultSet.getString("ngayBatDau"));
				congViec.setNgayKetThuc(resultSet.getString("ngayKetThuc"));
				DuAn duAn = new DuAn();
				duAn.setId(resultSet.getInt("id_duan"));
				congViec.setDuAn(duAn);
				TrangThai trangThai = new TrangThai();
				trangThai.setId(resultSet.getInt("id_trangthai"));
				congViec.setTrangThai(trangThai);
				listCongViec.add(congViec);
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
		
		return listCongViec;
	}
	
	public int modifyAtIdUserLevel(	int id, String ngayBatDau, 
			String ngayKetThuc, int id_trangthai) {

		int count = 0;
		String query = "UPDATE congviec \r\n"
		+ "SET ngayBatDau=?, ngayKetThuc=?, id_trangthai=? \r\n"
		+ "WHERE id=?;";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, ngayBatDau);
		statement.setString(2, ngayKetThuc);
		statement.setInt(3, id_trangthai);
		statement.setInt(4, id);
		
		count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return count;
	}
}
