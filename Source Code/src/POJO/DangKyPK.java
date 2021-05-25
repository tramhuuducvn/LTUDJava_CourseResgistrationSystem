package POJO;

import java.io.Serializable;

public class DangKyPK implements Serializable{
	private int mathongtin;
	private String mssv;
	
	public DangKyPK(int mathongtin, String mssv) {
		this.mathongtin = mathongtin;
		this.mssv = mssv;
	}
	
	public int getMathongtin() {
		return mathongtin;
	}
	public void setMathongtin(int mathongtin) {
		this.mathongtin = mathongtin;
	}
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	
}
