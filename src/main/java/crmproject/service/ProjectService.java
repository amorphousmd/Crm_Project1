package crmproject.service;

import java.util.List;

import crmproject.entity.DuAn;
import crmproject.repository.DuAnRepository;

public class ProjectService {
	private DuAnRepository duAnRepository = new DuAnRepository();
	// Grab all table entries service.
	public List<DuAn> getProjectTable() {
		List<DuAn> listDuAn = duAnRepository.findAll();
		
		return listDuAn;
	}
	
	// Delete an entry from table by ID.
	public boolean deleteRoleById(int id) {
		int count = duAnRepository.deleteAtId(id);
		
		return count > 0;
	}
	
	// Add profile service.
	public boolean addProject(	String ten, String mota,
								String ngayBatDau, String ngayKetThuc, 
								int idNguoiQuanLy, String trangThai ) {
		
		int idTrangThai = 1;
		
		// Convert String to int.
		switch (trangThai) {
		case "Chưa thực hiện":
			idTrangThai = 3;
			break;
		
		case "Đang thực hiện":
			idTrangThai = 1;
			break;
		
		case "Đã hoàn thành":
			idTrangThai = 2;
			break;
		default:
			break;
		}
		boolean isSuccess = duAnRepository.insertAllFields(	ten, mota,
															ngayBatDau, ngayKetThuc,
															idNguoiQuanLy, idTrangThai);
		return isSuccess;
	}
	
}
