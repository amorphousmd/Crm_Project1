package crmproject.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmproject.config.MysqlConfig;
import crmproject.entity.DuAn;
import crmproject.entity.NguoiDung;
import crmproject.entity.TrangThai;

public class DuAnRepository {
	// Get all project entries.
	public List<DuAn> findAll() {
		String query = "SELECT *\r\n"
				+ "FROM duan d;";
		Connection connection = MysqlConfig.getConnection();
		List<DuAn> listDuAn = new ArrayList<DuAn>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				DuAn duAn = new DuAn();
				duAn.setId(resultSet.getInt("id"));
				duAn.setTen(resultSet.getString("ten"));
				duAn.setMota(resultSet.getString("mota"));
				duAn.setNgayBatDau(resultSet.getString("ngayBatDau"));
				duAn.setNgayKetThuc(resultSet.getString("ngayKetThuc"));
				NguoiDung nguoiDung = new NguoiDung();
				nguoiDung.setId(resultSet.getInt("id_nguoiquanly"));
				duAn.setNguoiQuanLy(nguoiDung);
				TrangThai trangThai = new TrangThai();
				trangThai.setId(resultSet.getInt("id_trangthai"));
				duAn.setTrangThai(trangThai);
				listDuAn.add(duAn);
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
		
		return listDuAn;
	}
	
	// Insert all possible field in a entry.
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
	
	// Delete a row from table, specified by the ID.
	public int deleteAtId(int id) {
		int count = 0;
		String query = "DELETE FROM duan WHERE id = ?;";
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
