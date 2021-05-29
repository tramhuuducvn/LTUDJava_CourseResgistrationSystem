package POJO;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "SinhVien")
public class SinhVien implements Serializable{
	@Id
	@Column(name = "mssv", length = 12)
	private String mssv;
	
	@Column(name = "hoten", length = 50)
	private String hoten;
	@Column(name = "gioitinh", length = 3)
	private String gioitinh;
	@Column(name = "taikhoan", length = 50, unique = true)
	private String taikhoan; 
	@Column(name = "matkhau", length = 50)
	private String matkhau;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lop")
	private LopHoc lop;
	
	@Column(name = "email", length = 50)
	private String email;
	
	@ManyToMany
	@JoinTable(
		name = "DangKy",
		joinColumns = @JoinColumn(name = "mssv"),
		inverseJoinColumns = @JoinColumn(name = "mathongtin")
	)
	private Set<LichHoc> DanhSachLichHoc;
	
	
	
//----------------------------------------------------------------------
	public SinhVien() {}
	
	public SinhVien(String mssv, String hoten, String gioitinh, LopHoc lop) {
		this.mssv = mssv;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.lop = lop;
		this.taikhoan = "SV" + mssv;
		this.email = mssv + "@student.hcmus.edu.vn";
		this.matkhau = mssv;
	}
	
	public SinhVien(String mssv, String hoten, String gioitinh, String taikhoan, String matkhau, LopHoc lop, String email) {
		super();
		this.mssv = mssv;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.taikhoan = taikhoan;
		this.matkhau = matkhau;
		this.lop = lop;
		this.email = email;
	}
//-----------------------------------------------------------------
	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(String taikhoan) {
		this.taikhoan = taikhoan;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public LopHoc getLop() {
		return lop;
	}

	public void setLop(LopHoc lop) {
		this.lop = lop;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<LichHoc> getDanhSachLichHoc() {
		return DanhSachLichHoc;
	}

	public void setDanhSachLichHoc(Set<LichHoc> danhSachLichHoc) {
		DanhSachLichHoc = danhSachLichHoc;
	}
	public String toString() {
		return this.mssv + "_" + this.hoten;
	}
	//--------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
