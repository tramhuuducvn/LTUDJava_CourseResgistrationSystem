package POJO;

import java.io.*;

public class HocKyPK implements Serializable{
	private int namhoc;
	private String mahocky;
	
	public HocKyPK() {
		this.namhoc = 0;
		this.mahocky = "???";
	}
	
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