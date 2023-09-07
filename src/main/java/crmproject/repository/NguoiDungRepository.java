package crmproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmproject.config.MysqlConfig;
import crmproject.entity.LoaiThanhVien;
import crmproject.entity.NguoiDung;


public class NguoiDungRepository {
	public List<NguoiDung> findAll() {
		String query = "SELECT * \r\n"
				+ "FROM nguoidung n; ";
		Connection connection = MysqlConfig.getConnection();
		List<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				NguoiDung nguoiDung = new NguoiDung();
				nguoiDung.setId(resultSet.getInt("id"));
				nguoiDung.setEmail(resultSet.getString("email"));
				nguoiDung.setMatkhau(resultSet.getString("matkhau"));
				nguoiDung.setFullname(resultSet.getString("fullname"));
				nguoiDung.setDiachi(resultSet.getString("diachi"));
				nguoiDung.setSoDienThoai(resultSet.getString("soDienThoai"));
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setId(resultSet.getInt("id_loaithanhvien"));
				nguoiDung.setLoaiThanhVien(loaiThanhVien);
				
				listNguoiDung.add(nguoiDung);
			}
			
		} catch (SQLException e) {
			System.out.println("Query execution error" + e.getLocalizedMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Loi dong ket noi" + e.getLocalizedMessage());
				}
			}
		}
		
		return listNguoiDung;
	}
	
	public boolean insertAllFields(	String email, String matkhau, 
									String fullname, String diachi, 
									String soDienThoai, int idLoaiThanhVien) {
		String query = "INSERT INTO NguoiDung(email, matkhau, fullname, diachi, soDienThoai, id_loaithanhvien)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, matkhau);
			statement.setString(3, fullname);
			statement.setString(4, diachi);
			statement.setString(5, soDienThoai);
			statement.setInt(6, idLoaiThanhVien);
			
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
	
	public List<NguoiDung> findByEmailandPassword(String email, String matkhau) {
		String query = "SELECT *\r\n"
				+ "FROM nguoidung nd\r\n"
				+ "JOIN loaithanhvien l ON l.id = nd.id_loaithanhvien\r\n"
				+ "WHERE nd.email = ? AND nd.matkhau = ?;";
		
		List<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, matkhau);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				NguoiDung nguoiDung = new NguoiDung();
				nguoiDung.setId(resultSet.getInt("id"));
				nguoiDung.setFullname(resultSet.getString("fullname"));
				nguoiDung.setEmail(resultSet.getString("email"));
				
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setTen(resultSet.getString("ten"));
				nguoiDung.setLoaiThanhVien(loaiThanhVien);
				
				listNguoiDung.add(nguoiDung);
			}
			

			
		} catch (SQLException e) {
			System.out.println("Query execution error" + e.getLocalizedMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Loi dong ket noi" + e.getLocalizedMessage());
				}
			}
		}
		
		return listNguoiDung;
	}
	
	public int deleteAtId(int id) {
		int count = 0;
		String query = "DELETE FROM nguoidung WHERE id = ?;";
		
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
	
	public int modifyAtId(	int id, String email,
							String fullname, String diachi, 
							String soDienThoai, int idLoaiThanhVien) {
		int count = 0;
		String query = "UPDATE nguoidung\r\n"
				+ "SET email=?, fullname=?, diachi=?, soDienThoai = ?, id_loaithanhvien=?\r\n"
				+ "WHERE id=?;";
		
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, fullname);
			statement.setString(3, diachi);
			statement.setString(4, soDienThoai);
			statement.setInt(5, idLoaiThanhVien);
			statement.setInt(6, id);
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
