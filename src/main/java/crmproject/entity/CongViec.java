package crmproject.entity;

public class CongViec {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(String ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public String getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(String ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public DuAn getDuAn() {
		return duAn;
	}
	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}
	public TrangThai getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}
	private int id;
	private String ten;
	private String mota;
	private String ngayBatDau;
	private String ngayKetThuc;
	private DuAn duAn;
	private TrangThai trangThai;
}
