package POJO;

import java.io.*;
import java.util.*;
import javax.persistence.*;

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
	
	public int getNamhoc() {
		return namhoc;
	}

	public void setNamhoc(int namhoc) {
		this.namhoc = namhoc;
	}

	public String getMahocky() {
		return mahocky;
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
