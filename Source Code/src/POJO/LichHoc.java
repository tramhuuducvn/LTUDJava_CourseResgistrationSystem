package POJO;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "LichHoc")
public class LichHoc implements Serializable{
	@Id
	@Column(name = "mathongtin")
	private int mathongtin;
	@ManyToOne(targetEntity = HocKy.class)
	@JoinColumns({@JoinColumn(name = "namhoc", referencedColumnName = "namhoc"),
	              @JoinColumn(name = "hocky", referencedColumnName = "mahocky")})
	private HocKy hocky;
	@ManyToOne
	@JoinColumn(name = "mamonhoc")
	private MonHoc monhoc;
	@Column(name = "giaovien", length = 50)
	private String giaovien;
	@Column(name = "phonghoc", length = 5)
	private String phonghoc;
	@Column(name = "ngaythu")
	private int ngaythu;
	@Column(name = "cahoc")
	private int cahoc;	
	@Column(name = "slots")
	private int slots;
	
	@ManyToMany(targetEntity = SinhVien.class, mappedBy = "DanhSachLichHoc")
	private Set<SinhVien> DanhSachSinhVien;
	
	@ManyToMany(targetEntity = DotDangKyHocPhan.class,mappedBy = "DanhSachHocPhan")
	private Set<DotDangKyHocPhan> DanhSachDDKHP;
	
	//-------------------------------------------------------------
	public int getMathongtin() {
		return mathongtin;
	}

	public void setMathongtin(int mathongtin) {
		this.mathongtin = mathongtin;
	}

	public HocKy getHocky() {
		return hocky;
	}

	public void setHocky(HocKy hocky) {
		this.hocky = hocky;
	}

	public MonHoc getMonhoc() {
		return monhoc;
	}

	public void setMonhoc(MonHoc monhoc) {
		this.monhoc = monhoc;
	}

	public String getGiaovien() {
		return giaovien;
	}

	public void setGiaovien(String giaovien) {
		this.giaovien = giaovien;
	}

	public String getPhonghoc() {
		return phonghoc;
	}

	public void setPhonghoc(String phonghoc) {
		this.phonghoc = phonghoc;
	}

	public int getNgaythu() {
		return ngaythu;
	}

	public void setNgaythu(int ngaythu) {
		this.ngaythu = ngaythu;
	}

	public int getCahoc() {
		return cahoc;
	}

	public void setCahoc(int cahoc) {
		this.cahoc = cahoc;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public Set<SinhVien> getDanhSachSinhVien() {
		return DanhSachSinhVien;
	}

	public void setDanhSachSinhVien(Set<SinhVien> danhSachSinhVien) {
		DanhSachSinhVien = danhSachSinhVien;
	}
	
	public String toString() {
		return this.mathongtin + ", " + this.hocky.toString() + ", " + this.monhoc.toString() + ", " + this.giaovien + ", " + this.ngaythu + ", " + this.cahoc + ", " + this.phonghoc; 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
