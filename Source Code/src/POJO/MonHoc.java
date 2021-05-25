package POJO;

import java.io.*;
import java.util.*;
import javax.persistence.*;


@Entity
@Table(name = "MonHoc")
public class MonHoc implements Serializable{
	@Id
	@Column(name = "mamonhoc", length = 10)
	private String mamonhoc;
	@Column(name = "tenmonhoc", length = 100)
	private String tenmonhoc;
	@Column(name = "sotinchi")
	private int sotinchi;
	//---------------------------------------------------------
	public String getMamonhoc() {
		return mamonhoc;
	}


	public void setMamonhoc(String mamonhoc) {
		this.mamonhoc = mamonhoc;
	}


	public String getTenmonhoc() {
		return tenmonhoc;
	}


	public void setTenmonhoc(String tenmonhoc) {
		this.tenmonhoc = tenmonhoc;
	}


	public int getSotinchi() {
		return sotinchi;
	}


	public void setSotinchi(int sotinchi) {
		this.sotinchi = sotinchi;
	}
	
	public String toString() {
		return this.mamonhoc + ", " + this.tenmonhoc + ",TC: " + this.sotinchi;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World");
	}

}
