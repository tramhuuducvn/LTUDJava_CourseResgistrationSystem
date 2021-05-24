package POJO;

import java.io.*;
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
	@Column(name = "taikhoan", length = 50)
	private String taikhoan; 
	@Column(name = "matkhau", length = 50)
	private String matkhau;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "malop")
	private LopHoc lop;
	@Column(name = "email", length = 50)
	private String email;
//----------------------------------------------------------------------
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
//-----------------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
