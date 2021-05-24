package POJO;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "DangKy")
public class DangKy implements Serializable{
	@Id
	@Column(name = "mathongtin")
	private int mathongtin;
	
	@Column(name = "namhoc")
	private int namhoc;
	@Column(name = "hocky", length = 3)
	private String hocky;
	@Column(name = "mamonhoc", length = 10)
	private String mamonhoc;
	@Column(name = "giaovien", length = 50)
	private String giaovien;
	@Column(name = "phonghoc", length = 5)
	private String phonghoc;
	@Column(name = "ngaythu")
	private int ngaythu;
	@Column(name = "cahoc")
	private int cahoc;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "mssv")
	private String mssv;
	//Method-----------------------------------------------------
	
	
	//-----------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
