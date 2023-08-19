package crmproject.service;

import crmproject.repository.LoaiThanhVienRepository;

public class QuyenService {
	private LoaiThanhVienRepository loaiThanhVienRepository = new LoaiThanhVienRepository();
	public boolean addRole(String rolename, String description) {
		boolean isSuccess = loaiThanhVienRepository.insertNameAndDescription(rolename, description);
		return isSuccess;
	}
}
