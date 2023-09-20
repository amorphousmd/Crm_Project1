package crmproject.service;

import crmproject.repository.CongViecNguoiDungRepository;

public class TaskUserService {
	private CongViecNguoiDungRepository congViecNguoiDungRepository = 
			new CongViecNguoiDungRepository();
	
	public boolean addEntry(int id_congviec, int id_nguoidung) {

		boolean isSuccess = congViecNguoiDungRepository.insertAllFields(id_congviec, id_nguoidung);
		return isSuccess;
	}
}
