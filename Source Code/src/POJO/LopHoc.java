package POJO;

import java.io.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "LopHoc")
public class LopHoc implements Serializable{
	// Attribute----------------------------------------------
	@Id
	@Column(name = "malop", length = 7)
	private String malop;
	
	@Column(name = "siso")
	private int siso;
	@Column(name = "sisonam")
	private int sisonam;
	@Column(name = "sisonu")
	private int sisonu;
	
	@OneToMany(mappedBy = "lop")
	private Set<SinhVien> DanhSachSinhVien;
	// Method-------------------------------------------------
	public LopHoc() {
		this.malop = "19CTT3";
	}
	
	
	public String getMalop() {
		return malop;
	}

	public void setMalop(String malop) {
		this.malop = malop;
	}

	public int getSiso() {
		return siso;
	}

	public void setSiso(int siso) {
		this.siso = siso;
	}

	public int getSisonam() {
		return sisonam;
	}

	public void setSisonam(int sisonam) {
		this.sisonam = sisonam;
	}

	public int getSisonu() {
		return sisonu;
	}

	public void setSisonu(int sisonu) {
		this.sisonu = sisonu;
	}

	public Set<SinhVien> getDanhSachSinhVien() {
		return DanhSachSinhVien;
	}

	public void setDanhSachSinhVien(Set<SinhVien> danhSachSinhVien) {
		DanhSachSinhVien = danhSachSinhVien;
	}
	
	@Override
	public String toString() {
		return this.malop;
	}
	
	public static void main(String[] args) {
		LopHoc lop = new LopHoc();
		System.out.println(lop.toString());
	}
}
