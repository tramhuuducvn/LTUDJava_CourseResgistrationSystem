package POJO;

import java.io.*;
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
	
	public static void main(String[] args) {
		System.out.println("Hello World");
	}

}
