package crmproject.entity;

import java.util.List;

public class DetailRow {
	private DuAn duAn;
	private List<CongViec> listCongViecInProgress;
	private List<CongViec> listCongViecFinished;
	private List<CongViec> listCongViecNotStarted;
	public DuAn getDuAn() {
		return duAn;
	}
	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}
	public List<CongViec> getListCongViecInProgress() {
		return listCongViecInProgress;
	}
	public void setListCongViecInProgress(List<CongViec> listCongViecInProgress) {
		this.listCongViecInProgress = listCongViecInProgress;
	}
	public List<CongViec> getListCongViecFinished() {
		return listCongViecFinished;
	}
	public void setListCongViecFinished(List<CongViec> listCongViecFinished) {
		this.listCongViecFinished = listCongViecFinished;
	}
	public List<CongViec> getListCongViecNotStarted() {
		return listCongViecNotStarted;
	}
	public void setListCongViecNotStarted(List<CongViec> listCongViecNotStarted) {
		this.listCongViecNotStarted = listCongViecNotStarted;
	}
	public DetailRow(DuAn duAn, 
					 List<CongViec> listCongViecInProgress, 
					 List<CongViec> listCongViecFinished,
					 List<CongViec> listCongViecNotStarted) {
		
		this.duAn = duAn;
		this.listCongViecInProgress = listCongViecInProgress;
		this.listCongViecFinished = listCongViecFinished;
		this.listCongViecNotStarted = listCongViecNotStarted;
	}
	
}
