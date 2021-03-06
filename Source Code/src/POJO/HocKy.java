package POJO;

import java.io.*;
import java.util.*;
import javax.persistence.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;

@Entity
@Table(name = "HocKy")
@IdClass(HocKyPK.class)
public class HocKy implements Serializable{
	@Id
	@Column(name = "namhoc")
	private int namhoc;
	@Id
	@Column(name = "mahocky", length = 3)
	private String mahocky;
	
	@Column(name = "ngaybatdau")
	private Date ngaybatdau;
	@Column(name = "ngayketthuc")
	private Date ngayketthuc;
	@Column(name = "trangthai")
	private boolean trangthai;
	
	@OneToMany(targetEntity = LichHoc.class, mappedBy = "hocky")
	private Set<LichHoc> ThongTinLichHoc;
	
	@OneToMany(targetEntity = DotDangKyHocPhan.class, mappedBy = "hocky")
	private Set<DotDangKyHocPhan> DanhSachDDKHP;
	
	
	public HocKy() {}
	
	public HocKy(int namhoc, String mahocky, String ngaybatdau, String ngayketthuc) {
		this.namhoc = namhoc;
		this.mahocky = mahocky;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			this.ngaybatdau = df.parse(ngaybatdau);
			this.ngayketthuc= df.parse(ngayketthuc);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getNamhoc() {
		return namhoc;
	}

	public void setNamhoc(int namhoc) {
		this.namhoc = namhoc;
	}

	public String getMahocky() {
		return mahocky;
	}

	public boolean isTrangthai() {
		return trangthai;
	}
	
	public String getTrangThai() {
		if(trangthai) {
			return "Bật";
		}
		return "Tắt";
	}

	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}

	public Set<DotDangKyHocPhan> getDanhSachDDKHP() {
		return DanhSachDDKHP;
	}

	public void setDanhSachDDKHP(Set<DotDangKyHocPhan> danhSachDDKHP) {
		DanhSachDDKHP = danhSachDDKHP;
	}

	public void setMahocky(String mahocky) {
		this.mahocky = mahocky;
	}

	public Date getNgaybatdau() {
		return ngaybatdau;
	}

	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}

	public Date getNgayketthuc() {
		return ngayketthuc;
	}

	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}

	public Set<LichHoc> getThongTinLichHoc() {
		return ThongTinLichHoc;
	}

	public void setThongTinLichHoc(Set<LichHoc> thongTinLichHoc) {
		ThongTinLichHoc = thongTinLichHoc;
	}
	
	public String toString() {
		return this.namhoc + " - " + this.mahocky;
	}
	
	//-----------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
