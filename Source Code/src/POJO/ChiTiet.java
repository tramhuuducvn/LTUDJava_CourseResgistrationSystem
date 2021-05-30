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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
