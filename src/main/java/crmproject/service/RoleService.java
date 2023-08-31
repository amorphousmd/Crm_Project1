package crmproject.service;

import java.util.List;

import crmproject.entity.LoaiThanhVien;
import crmproject.repository.LoaiThanhVienRepository;

public class RoleService {
	private LoaiThanhVienRepository loaiThanhVienRepository = new LoaiThanhVienRepository();
	// Add role service.
	public boolean addRole(String rolename, String description) {
		boolean isSuccess = loaiThanhVienRepository.insertNameAndDescription(rolename, description);
		return isSuccess;
	}
	
	// Grab all table entries service.
	public List<LoaiThanhVien> getRoleTable() {
		List<LoaiThanhVien> listLoaiThanhVien = loaiThanhVienRepository.findAll();
		
		return listLoaiThanhVien;
	}
	
	// Delete an entry from table by ID.
	public boolean deleteRoleById(int id) {
		int count = loaiThanhVienRepository.deleteAtId(id);
		
		return count > 0;
	}
	
	// Modify an entry from table by ID.
	public boolean modifyRoleById(	int id,
									String modifiedName, 
									String modifiedDescription) {
		int count = loaiThanhVienRepository.modifyAtId(id, modifiedName, modifiedDescription);
		
		return count > 0;
	}
}
