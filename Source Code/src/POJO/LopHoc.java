package POJO;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "Lophoc")
public class LopHoc implements Serializable{
	// Attribute----------------------------------------------
	@Id
	@Column(name = "malop", length = 7)
	private String malop;
	
	@Column(name = "siso")
	private int siso;
	@Column(name = "sisonam")
	private int sisonam;
	@Column(name = "sisonu")
	private int sisonu;
	
	// Method-------------------------------------------------
	
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
