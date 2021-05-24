package POJO;

import java.io.*;
import java.util.*;

import javax.persistence.*;


class HocKyPK{
	private int namhoc;
	private String mahocky;
	
	public HocKyPK(int year, String seme) {
		this.namhoc = year;
		this.mahocky = seme;
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
	public void setMahocky(String mahocky) {
		this.mahocky = mahocky;
	}
}

@Entity
@Table(name = "HocKy")
@IdClass(HocKyPK.class)
public class HocKy {
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
	
	
//-----------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
