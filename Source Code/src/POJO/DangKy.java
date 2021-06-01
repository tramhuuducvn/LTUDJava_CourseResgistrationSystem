package POJO;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "DangKy")
@IdClass(DangKyPK.class)
public class DangKy implements Serializable{
	@Id
	@Column(name = "mathongtin")
	private int mathongtin;
	@Id
	@JoinColumn(name = "mssv")
	private String mssv;
	//Method-----------------------------------------------------	
	public DangKy() {}
	
	
	public DangKy(int mathongtin, String mssv) {
		this.mathongtin = mathongtin;
		this.mssv = mssv;
	}


	//-----------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
