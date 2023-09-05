package crmproject.entity;

public class DuAn {
	private int id;
	private String ten;
	private String mota;
	private String ngayBatDau;
	private String ngayKetThuc;
	private NguoiDung nguoiQuanLy;
	private TrangThai trangThai;
	
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
	public NguoiDung getNguoiQuanLy() {
		return nguoiQuanLy;
	}
	public void setNguoiQuanLy(NguoiDung nguoiQuanLy) {
		this.nguoiQuanLy = nguoiQuanLy;
	}
	public TrangThai getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThai trangThai) {
		this.trangThai = trangThai;
	}
}
