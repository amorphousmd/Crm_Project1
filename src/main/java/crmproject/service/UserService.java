package crmproject.service;

import java.util.List;

import crmproject.entity.NguoiDung;
import crmproject.repository.NguoiDungRepository;

public class UserService {
	private NguoiDungRepository nguoiDungRepository = new NguoiDungRepository();
	// Add an user.
	public boolean addUser(	String email, String matkhau, 
							String fullname, String diachi, 
							String soDienThoai, int idLoaiThanhVien) {
		
		boolean isSuccess = nguoiDungRepository.insertAllFields(email, matkhau, 
																fullname, diachi, 
																soDienThoai, idLoaiThanhVien);
		return isSuccess;
	}
	
	// Find all users.
	public List<NguoiDung> getUserTable() {
		List<NguoiDung> listNguoiDung = nguoiDungRepository.findAll();
		
		return listNguoiDung;
	}
}
