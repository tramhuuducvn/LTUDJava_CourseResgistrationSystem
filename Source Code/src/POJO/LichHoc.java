package POJO;

import java.io.*;
import javax.persistence.*;

class LichHocPK implements Serializable{
	private int ngaythu;
	private int cahoc;
	private String phonghoc;
	
	public LichHocPK(int ngay, int ca, String phong) {
		this.ngaythu = ngay;
		this.cahoc = ca;
		this.phonghoc = phong;
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
	public String getPhonghoc() {
		return phonghoc;
	}
	public void setPhonghoc(String phonghoc) {
		this.phonghoc = phonghoc;
	}
}

@Entity
@Table(name = "LichHoc")
@IdClass(LichHocPK.class)
public class LichHoc implements Serializable{
	@Id
	@Column(name = "ngaythu")
	private int ngaythu;
	@Id
	@Column(name = "cahoc")
	private int cahoc;
	@Id
	@Column(name = "phonghoc", length = 5)
	private String phonghoc;
	@Column(name = "slots")
	private int slots;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
