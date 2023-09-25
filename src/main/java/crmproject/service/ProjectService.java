package crmproject.service;

import java.util.ArrayList;
import java.util.List;

import crmproject.entity.DuAn;
import crmproject.repository.DuAnNguoiDungRepository;
import crmproject.repository.DuAnRepository;

public class ProjectService {
	private DuAnRepository duAnRepository = new DuAnRepository();
	private DuAnNguoiDungRepository duAnNguoiDungRepository = new DuAnNguoiDungRepository();
	
	// Grab all table entries service.
	public List<DuAn> getProjectTable() {
		List<DuAn> listDuAn = duAnRepository.findAll();
		
		return listDuAn;
	}
	
	// Grab all table entries service. (Sorted in types)
	public List<List<DuAn>> getProjectTableSorted() {
		List<DuAn> listDuAnUnSorted = duAnRepository.findAll();
		List<List<DuAn>> listDuAnSorted = new ArrayList<List<DuAn>>();
		List<DuAn> listDuAnInProgress = new ArrayList<DuAn>();
		List<DuAn> listDuAnFinished = new ArrayList<DuAn>();
		List<DuAn> listDuAnNotStarted = new ArrayList<DuAn>();
		
		for (DuAn duAn : listDuAnUnSorted) {
			int projectStatus = duAn.getTrangThai().getId();
			switch (projectStatus) {
			
			case 1:
			{
				listDuAnInProgress.add(duAn);
				break;
			}
			
			case 2:
			{
				listDuAnFinished.add(duAn);
				break;
			}
			
			case 3:
			{
				listDuAnNotStarted.add(duAn);
				break;
			}
			default:
				break;
			}
        }
		listDuAnSorted.add(0,listDuAnInProgress);
		listDuAnSorted.add(1,listDuAnFinished);
		listDuAnSorted.add(2,listDuAnNotStarted);
		return listDuAnSorted;
	}
	
	// Delete an entry from table by ID.
	public boolean deleteRoleById(int id) {
		int count = duAnRepository.deleteAtId(id);
		
		return count > 0;
	}
	
	// Grab all entries with the specified user ID.
	public List<DuAn> getProjectsWithUserId(int id) {
		List<DuAn> listDuAn = new ArrayList<DuAn>();
		List<Integer> projectIdsList = duAnNguoiDungRepository.findAllIdWithUserId(id);
		for (Integer projectId : projectIdsList) {
			listDuAn.add(duAnRepository.findAtId(projectId));
        }
		return listDuAn;
	}
	
	public List<List<DuAn>> getSortedProjectsWithUserId(int id) {
		List<List<DuAn>> listDuAnSorted = new ArrayList<List<DuAn>>();
		List<DuAn> listDuAnInProgress = new ArrayList<DuAn>();
		List<DuAn> listDuAnFinished = new ArrayList<DuAn>();
		List<DuAn> listDuAnNotStarted = new ArrayList<DuAn>();
		List<Integer> projectIdsList = duAnNguoiDungRepository.findAllIdWithUserId(id);
		for (Integer projectId : projectIdsList) {
			DuAn duAn = duAnRepository.findAtId(projectId);
			int projectStatus = duAn.getTrangThai().getId();
			switch (projectStatus) {
			
			case 1:
			{
				listDuAnInProgress.add(duAn);
				break;
			}
			
			case 2:
			{
				listDuAnFinished.add(duAn);
				break;
			}
			
			case 3:
			{
				listDuAnNotStarted.add(duAn);
				break;
			}
			default:
				break;
			}
        }
		listDuAnSorted.add(0,listDuAnInProgress);
		listDuAnSorted.add(1,listDuAnFinished);
		listDuAnSorted.add(2,listDuAnNotStarted);
		return listDuAnSorted;
	}
	
	// Add profile service.
	public boolean addProject(	String ten, String mota,
								String ngayBatDau, String ngayKetThuc, 
								int idNguoiQuanLy, String trangThai ) {
		
		int idTrangThai = 1;
		
		// Convert String to int.
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
	
	// Modify an entry from table by ID.
	public boolean modifyUserById(	int id, String ten,
									String mota, String ngayBatDau, 
									String ngayKetThuc, int id_nguoiquanly, 
									int id_trangthai ) {
		
		int count = duAnRepository.modifyAtId(	id, ten, 
												mota, ngayBatDau, 
												ngayKetThuc, id_nguoiquanly,
												id_trangthai);
		return count > 0;
	}
	
	// Modify an entry from table by ID. (User Level)
	public boolean modifyUserByIdUserLevel(	int id, String ngayBatDau, 
											String ngayKetThuc, int id_trangthai ) {
		
		int count = duAnRepository.modifyAtIdUserLevel(	id, ngayBatDau, 
														ngayKetThuc, id_trangthai);
		return count > 0;
	}
}
