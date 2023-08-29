package crmproject.service;

import java.util.List;

import crmproject.entity.LoaiThanhVien;
import crmproject.repository.LoaiThanhVienRepository;

public class RoleService {
	private LoaiThanhVienRepository loaiThanhVienRepository = new LoaiThanhVienRepository();
	public boolean addRole(String rolename, String description) {
		boolean isSuccess = loaiThanhVienRepository.insertNameAndDescription(rolename, description);
		return isSuccess;
	}
	
	public List<LoaiThanhVien> getRoleTable() {
		List<LoaiThanhVien> listLoaiThanhVien = loaiThanhVienRepository.findAll();
		
		return listLoaiThanhVien;
	}
	
	public boolean deleteRoleById(int id) {
		int count = loaiThanhVienRepository.delelteById(id);
		
		return count > 0;
	}
}
