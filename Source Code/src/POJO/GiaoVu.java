package POJO;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "GiaoVu")
public class GiaoVu implements Serializable {
	// Attribute-----------------------------------------
	@Id
	@Column (name = "magv", length = 12)
	private String magv;
	@Column (name = "hoten", length = 50)
	private String hoten;
	@Column (name = "taikhoan", length = 50, unique = true)
	private String taikhoan;
	@Column (name = "matkhau", length = 50)
	private String matkhau;
	//Method -------------------------------------------------------	
	public String getMagv() {
		return magv;
	}

	public void setMagv(String magv) {
		this.magv = magv;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
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
	
	public String toString() {
		return magv + "_" + hoten;
	}
	
	//--------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
