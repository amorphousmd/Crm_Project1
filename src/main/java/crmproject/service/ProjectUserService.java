package crmproject.service;

import crmproject.repository.DuAnNguoiDungRepository;

public class ProjectUserService {
	private DuAnNguoiDungRepository duAnNguoiDungRepository= new DuAnNguoiDungRepository();
	
	// Insert an entry at the end of the table.
	public boolean addEntry(int id_duan, int id_nguoidung) {

		boolean isSuccess = duAnNguoiDungRepository.insertAllFields(id_duan, id_nguoidung);
		return isSuccess;
	}
}
