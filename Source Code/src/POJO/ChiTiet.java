package POJO;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "ChiTiet")
public class ChiTiet implements Serializable{
	@Id
	@Column(name = "mathongtin")
	private int mathongtin;
	@Id
	@Column(name = "madot")
	private int madot;
	
	public ChiTiet() {}	
	
	public ChiTiet(int mathongtin, int madot) {
		this.mathongtin = mathongtin;
		this.madot = madot;
	}


	public int getMathongtin() {
		return mathongtin;
	}

	public void setMathongtin(int mathongtin) {
		this.mathongtin = mathongtin;
	}

	public int getMadot() {
		return madot;
	}

	public void setMadot(int madot) {
		this.madot = madot;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
