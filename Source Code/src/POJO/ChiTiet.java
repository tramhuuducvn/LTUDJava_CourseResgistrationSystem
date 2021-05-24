package POJO;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "ChiTiet")
public class ChiTiet {
	@Column(name = "namhoc")
	private int namhoc;
	@Column(name = "hocky", length = 3)
	private String hocky;
	@Column(name = "mamonhoc", length = 10)
	private String mamonhoc;
	@Column(name = "giaovien", length = 50)
	private String giaovien;
	@Column(name = "slot")
	private int slot;
	@Column(name = "phonghoc", length = 5)
	private String phonghoc;
	@Column(name = "ngaythu")
	private int ngaythu;
	@Column(name = "cahoc")
	private int cahoc;
	@Column(name = "mssv", length = 12)
	private String mssv;
	
	//-----------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
