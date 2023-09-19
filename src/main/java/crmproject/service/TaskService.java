package crmproject.service;

import java.util.List;

import crmproject.entity.CongViec;
import crmproject.repository.CongViecRepository;

public class TaskService {
	private CongViecRepository congViecRepository = new CongViecRepository();
	// Grab all table entries service.
	public List<CongViec> getTaskTable() {
		List<CongViec> listCongViec = congViecRepository.findAll();
		
		return listCongViec;
	}
	
	// Delete an entry from table by ID.
	public boolean deleteRoleById(int id) {
		int count = congViecRepository.deleteAtId(id);
		
		return count > 0;
	}
		
		
	// Modify an entry from table by ID.
	public boolean modifyUserById(	int id, String ten,
									String mota, String ngayBatDau, 
									String ngayKetThuc, int id_duan, 
									int id_trangthai ) {
		
		int count = congViecRepository.modifyAtId(	id, ten, 
												mota, ngayBatDau, 
												ngayKetThuc, id_duan,
												id_trangthai);
		return count > 0;
	}
}
