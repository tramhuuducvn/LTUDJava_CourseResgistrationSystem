package POJO;

import java.io.*;
import java.sql.*;

import javax.persistence.*;

@Entity
@Table(name = "DangKy")
@IdClass(HocKyPK.class)
public class DangKy {
	@Id
	@Column(name = "namhochientai")
	private int namhochientai;
	@Id
	@Column(name = "hockyhientai", length = 3)
	private String hockyhientai;
	
	@Column(name = "thoigianbatdaudangky")
	private Timestamp thoigianbatdaudangky;
	@Column(name = "thoigianketthucdangky")
	private Timestamp thoigianketthucdangky;
//--------------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
