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
	public List<NguoiDung> findByEmailandPassword(String email, String matkhau) {
		String query = "SELECT *\r\n"
				+ "FROM nguoidung nd\r\n"
				+ "JOIN loaithanhvien l ON l.id = nd.id_loaithanhvien\r\n"
				+ "WHERE nd.email = ? AND nd.matkhau = ?;";
		
		Connection connection = MysqlConfig.getConnection();
		List<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
		
		try {
			// Truyen query vao trong connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			// Truyen gia tri tham so vao query neu co (Cho nao co ? la co tham so)
			statement.setString(1, email); // setInt, setString phai phu thuoc vao kieu du lieu cua cot dang so sanh tham so
			statement.setString(2, matkhau);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				NguoiDung nguoiDung = new NguoiDung();
//				int id = resultSet.getInt("id");
				nguoiDung.setId(resultSet.getInt("id"));
				nguoiDung.setFullname(resultSet.getString("fullname"));
				nguoiDung.setEmail(resultSet.getString("email"));
				
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setTen(resultSet.getString("ten"));
				nguoiDung.setLoaiThanhVien(loaiThanhVien);
				
				listNguoiDung.add(nguoiDung);
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
		
		return listNguoiDung;
	}
	
	
//	public List<NguoiDung> findByEmailandPassword(String email, String matkhau) {
//		String query = "SELECT *\r\n"
//				+ "FROM nguoidung nd\r\n"
//				+ "WHERE nd.email = ? AND nd.matkhau = ?;";
//		
//		Connection connection = MysqlConfig.getConnection();
//		List<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
//		
//		try {
//			// Truyen query vao trong connection
//			PreparedStatement statement = connection.prepareStatement(query);
//			
//			// Truyen gia tri tham so vao query neu co (Cho nao co ? la co tham so)
//			statement.setString(1, email); // setInt, setString phai phu thuoc vao kieu du lieu cua cot dang so sanh tham so
//			statement.setString(2, matkhau);
//			
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				NguoiDung nguoiDung = new NguoiDung();
////				int id = resultSet.getInt("id");
//				nguoiDung.setId(resultSet.getInt("id"));
//				nguoiDung.setFullname(resultSet.getString("fullname"));
//				nguoiDung.setEmail(resultSet.getString("email"));
//				
//				listNguoiDung.add(nguoiDung);
//			}
//			
//
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Query execution error" + e.getLocalizedMessage());
//		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					System.out.println("Loi dong ket noi" + e.getLocalizedMessage());
//				}
//			}
//		}
//		
//		return listNguoiDung;
//	}
}
