package crmproject.entity;

public class NguoiDung {
	private int id;
	private String email;
	private String matkhau;
	private String fullname;
	private String diachi;
	private String soDienThoai;
	private LoaiThanhVien loaiThanhVien;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public LoaiThanhVien getLoaiThanhVien() {
		return loaiThanhVien;
	}
	public void setLoaiThanhVien(LoaiThanhVien loaiThanhVien) {
		this.loaiThanhVien = loaiThanhVien;
	}
	
}
