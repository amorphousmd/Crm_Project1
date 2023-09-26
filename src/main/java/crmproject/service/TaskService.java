package crmproject.service;

import java.util.ArrayList;
import java.util.List;

import crmproject.entity.CongViec;
import crmproject.repository.CongViecNguoiDungRepository;
import crmproject.repository.CongViecRepository;

public class TaskService {
	private CongViecRepository congViecRepository = new CongViecRepository();
	private CongViecNguoiDungRepository congViecNguoiDungRepository = new CongViecNguoiDungRepository();
	// Grab all table entries service.
	public List<CongViec> getTaskTable() {
		List<CongViec> listCongViec = congViecRepository.findAll();
		
		return listCongViec;
	}
	
	// Grab all table entries service. (Sorted in types)
	public List<List<CongViec>> getTaskTableSorted() {
		List<CongViec> listCongViecUnSorted = congViecRepository.findAll();
		List<List<CongViec>> listCongViecSorted = new ArrayList<List<CongViec>>();
		List<CongViec> listCongViecInProgress = new ArrayList<CongViec>();
		List<CongViec> listCongViecFinished = new ArrayList<CongViec>();
		List<CongViec> listCongViecNotStarted = new ArrayList<CongViec>();
		
		for (CongViec congViec : listCongViecUnSorted) {
			int projectStatus = congViec.getTrangThai().getId();
			switch (projectStatus) {
			
			case 1:
			{
				listCongViecInProgress.add(congViec);
				break;
			}
			
			case 2:
			{
				listCongViecFinished.add(congViec);
				break;
			}
			
			case 3:
			{
				listCongViecNotStarted.add(congViec);
				break;
			}
			default:
				break;
			}
        }
		listCongViecSorted.add(0,listCongViecInProgress);
		listCongViecSorted.add(1,listCongViecFinished);
		listCongViecSorted.add(2,listCongViecNotStarted);
		return listCongViecSorted;
	}
	
	// Sorted Task List from ID
	public List<List<CongViec>> getSortedTasksWithUserId(int id) {
		List<List<CongViec>> listCongViecSorted = new ArrayList<List<CongViec>>();
		List<CongViec> listCongViecInProgress = new ArrayList<CongViec>();
		List<CongViec> listCongViecFinished = new ArrayList<CongViec>();
		List<CongViec> listCongViecNotStarted = new ArrayList<CongViec>();
		List<Integer> projectIdsList = congViecNguoiDungRepository.findAllIdWithUserId(id);
		for (Integer projectId : projectIdsList) {
			CongViec congViec = congViecRepository.findAtId(projectId);
			int projectStatus = congViec.getTrangThai().getId();
			switch (projectStatus) {
			
			case 1:
			{
				listCongViecInProgress.add(congViec);
				break;
			}
			
			case 2:
			{
				listCongViecFinished.add(congViec);
				break;
			}
			
			case 3:
			{
				listCongViecNotStarted.add(congViec);
				break;
			}
			default:
				break;
			}
        }
		listCongViecSorted.add(0,listCongViecInProgress);
		listCongViecSorted.add(1,listCongViecFinished);
		listCongViecSorted.add(2,listCongViecNotStarted);
		return listCongViecSorted;
	}
	
	// Grab all entries with the specified user ID.
	public List<CongViec> getTasksWithUserId(int id) {
		List<CongViec> listCongViec = new ArrayList<CongViec>();
		List<Integer> projectIdsList = congViecNguoiDungRepository.findAllIdWithUserId(id);
		for (Integer projectId : projectIdsList) {
			listCongViec.add(congViecRepository.findAtId(projectId));
        }
		return listCongViec;
	}
	
	// Grab all table entries service under a specified project.
	public List<CongViec> getTaskTableUnderProject(int projectId) {
		List<CongViec> listCongViec = congViecRepository.findUnderProject(projectId);
		
		return listCongViec;
	}
	
	// Grab all table entries service under a specified project.
	public List<List<CongViec>> getTaskTableUnderProjectSorted(int projectId) {
		List<CongViec> listCongViec = congViecRepository.findUnderProject(projectId);
		List<List<CongViec>> listCongViecSorted = new ArrayList<List<CongViec>>();
		List<CongViec> listCongViecInProgress = new ArrayList<CongViec>();
		List<CongViec> listCongViecFinished = new ArrayList<CongViec>();
		List<CongViec> listCongViecNotStarted = new ArrayList<CongViec>();
		
		for (CongViec congViec : listCongViec) {
			int projectStatus = congViec.getTrangThai().getId();
			switch (projectStatus) {
			
			case 1:
			{
				listCongViecInProgress.add(congViec);
				break;
			}
			
			case 2:
			{
				listCongViecFinished.add(congViec);
				break;
			}
			
			case 3:
			{
				listCongViecNotStarted.add(congViec);
				break;
			}
			default:
				break;
			}
        }
		listCongViecSorted.add(0,listCongViecInProgress);
		listCongViecSorted.add(1,listCongViecFinished);
		listCongViecSorted.add(2,listCongViecNotStarted);
		return listCongViecSorted;
	}
	
	public int getTaskLastestId() {
		int maxId = congViecRepository.findLatestId();
		
		return maxId;
	}
	
	// Add profile service.
	public boolean addTask(	String ten, String mota,
								String ngayBatDau, String ngayKetThuc, 
								int idDuAn ) {
		
		boolean isSuccess = congViecRepository.insertAllFields(	ten, mota,
															ngayBatDau, ngayKetThuc,
															idDuAn);
		return isSuccess;
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
	
	// Modify an entry from table by ID. (User Level)
	public boolean modifyUserByIdUserLevel(	int id, String ngayBatDau, 
											String ngayKetThuc, int id_trangthai ) {
		
		int count = congViecRepository.modifyAtIdUserLevel(	id, ngayBatDau, 
														ngayKetThuc, id_trangthai);
		return count > 0;
	}
}
