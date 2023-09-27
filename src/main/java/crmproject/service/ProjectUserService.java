package crmproject.service;

import crmproject.repository.DuAnNguoiDungRepository;

public class ProjectUserService {
	private DuAnNguoiDungRepository duAnNguoiDungRepository= new DuAnNguoiDungRepository();
	
	// Insert an entry at the end of the table.
	public boolean addEntry(int id_duan, int id_nguoidung) {

		boolean isSuccess = duAnNguoiDungRepository.insertAllFields(id_duan, id_nguoidung);
		return isSuccess;
	}
	
	// Delete an entry from table by a pair of IDs.
	public boolean removeEntry(int id_duan, int id_nguoidung) {
		int count = duAnNguoiDungRepository.deleteAtIds(id_duan, id_nguoidung);
		
		return count > 0;
	}
}
