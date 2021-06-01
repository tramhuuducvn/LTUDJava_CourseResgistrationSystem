package POJO;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "DotDangKyHocPhan")
public class DotDangKyHocPhan implements Serializable {
	@Id
	@Column(name = "madot")
	private int madot;
	@ManyToOne(targetEntity = HocKy.class)
	@JoinColumns({@JoinColumn(name = "namhoc", referencedColumnName = "namhoc"),
	              @JoinColumn(name = "hocky", referencedColumnName = "mahocky")})
	private HocKy hocky;
	
	@Column(name = "STT")
	private int STT;
	@Column(name = "ngaybatdaudangky")
	private Date ngaybatdaudangky;
	@Column(name = "ngayketthucdangky")
	private Date ngayketthucdangky;
	
	@ManyToMany(targetEntity = LichHoc.class)
	@JoinTable(
		name = "ChiTiet",
		joinColumns = @JoinColumn(name = "madot"),
		inverseJoinColumns = @JoinColumn(name = "mathongtin")
	)
	private Set<LichHoc> DanhSachHocPhan;
	
	
	public DotDangKyHocPhan() {}
	
	public DotDangKyHocPhan(int madot, HocKy hk, int stt, String ngaybatdau, String ngayketthuc) {
		this.madot = madot;
		this.hocky = hk;
		this.STT = stt;
		
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			this.ngaybatdaudangky = df.parse(ngaybatdau);
			this.ngayketthucdangky = df.parse(ngayketthuc);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public int getMadot() {
		return madot;
	}

	public void setMadot(int madot) {
		this.madot = madot;
	}

	public HocKy getHocky() {
		return hocky;
	}

	public void setHocky(HocKy hocky) {
		this.hocky = hocky;
	}

	public int getSTT() {
		return STT;
	}

	public void setSTT(int sTT) {
		STT = sTT;
	}

	public Date getNgaybatdaudangky() {
		return ngaybatdaudangky;
	}

	public void setNgaybatdaudangky(Date ngaybatdaudangky) {
		this.ngaybatdaudangky = ngaybatdaudangky;
	}

	public Date getNgayketthucdangky() {
		return ngayketthucdangky;
	}

	public void setNgayketthucdangky(Date ngayketthucdangky) {
		this.ngayketthucdangky = ngayketthucdangky;
	}

	public Set<LichHoc> getDanhSachHocPhan() {
		return DanhSachHocPhan;
	}

	public void setDanhSachHocPhan(Set<LichHoc> danhSachHocPhan) {
		DanhSachHocPhan = danhSachHocPhan;
	}	
	
	public String toNgayBatDau() {
//		return this.ngaybatdaudangky.getDate() + "/" + this.ngaybatdaudangky.getMonth() + "/" + this.ngaybatdaudangky.getYear();
		return this.ngaybatdaudangky.toString();
	}
	
	public String toNgayKetThuc() {
//		return this.ngayketthucdangky.getDate() + "/" + this.ngayketthucdangky.getMonth() + "/" + this.ngayketthucdangky.getYear();
		return this.ngayketthucdangky.toString();
	}
	@Override
	public String toString() {
		return String.valueOf(this.STT);
	}
	
	public String toLabel() {
		return "Danh sách học phần ĐK đợt " + this.STT + " của học kỳ " + this.hocky.toString() + " --- [YYYY-MM-DD] " + " Bắt đầu: " + this.toNgayBatDau() + " Kết thúc: " + this.toNgayKetThuc();  
	}
	

	public static void main(String[] args) {

	}

}
