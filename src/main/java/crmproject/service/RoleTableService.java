package crmproject.service;

import java.util.List;

import crmproject.entity.LoaiThanhVien;
import crmproject.repository.LoaiThanhVienRepository;

public class RoleTableService {
	private LoaiThanhVienRepository loaiThanhVienRepository = new LoaiThanhVienRepository();
	
	public List<LoaiThanhVien> getRoleTable() {
		List<LoaiThanhVien> listLoaiThanhVien = loaiThanhVienRepository.findAll();
		
		return listLoaiThanhVien;
	}
}
