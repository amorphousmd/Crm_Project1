package crmproject.service;

import crmproject.repository.DuAnRepository;

public class ProfileService {
	DuAnRepository duAnRepository = new DuAnRepository();
	// Add profile service.
	public boolean addProject(	String ten, String mota,
								String ngayBatDau, String ngayKetThuc, 
								int idNguoiQuanLy, String trangThai ) {
		
		int idTrangThai = 1;
		System.out.println(trangThai);
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
