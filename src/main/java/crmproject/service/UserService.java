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
	
	// Find an users.
	public NguoiDung getUserAtId(int id) {
		NguoiDung nguoiDung = nguoiDungRepository.findAtId(id);
		
		return nguoiDung;
	}
	
	// Delete an entry from table by ID.
	public boolean deleteUserById(int id) {
		int count = nguoiDungRepository.deleteAtId(id);
		
		return count > 0;
	}
	
	// Modify an entry from table by ID.
	public boolean modifyUserById(	int id, String email,
									String fullname, String diachi, 
									String soDienThoai, int idLoaiThanhVien ) {
		
		int count = nguoiDungRepository.modifyAtId(	id, email, 
													fullname, diachi, 
													soDienThoai, idLoaiThanhVien);
		return count > 0;
	}
}
