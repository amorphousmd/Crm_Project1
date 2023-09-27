package crmproject.service;

import crmproject.repository.CongViecNguoiDungRepository;

public class TaskUserService {
	private CongViecNguoiDungRepository congViecNguoiDungRepository = 
			new CongViecNguoiDungRepository();
	
	public boolean addEntry(int id_congviec, int id_nguoidung) {

		boolean isSuccess = congViecNguoiDungRepository.insertAllFields(id_congviec, id_nguoidung);
		return isSuccess;
	}
	
	// Delete an entry from table by a pair of IDs.
	public boolean removeEntry(int id_congviec, int id_nguoidung) {
		int count = congViecNguoiDungRepository.deleteAtIds(id_congviec, id_nguoidung);
		
		return count > 0;
	}
}
