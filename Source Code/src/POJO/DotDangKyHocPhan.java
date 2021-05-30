package POJO;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "DotDangKyHocPhan")
public class DotDangKyHocPhan implements Serializable {
	@Id
	@Column(name = "madot")
	private int madot;
	@ManyToOne(targetEntity = HocKy.class)
	@JoinColumns({@JoinColumn(name = "namhoc", referencedColumnName = "namhoc"),
	              @JoinColumn(name = "hocky", referencedColumnName = "mahocky")})
	private HocKy hocky;
	
	@Column(name = "STT")
	private int STT;
	@Column(name = "ngaybatdaudangky")
	private Date ngaybatdaudangky;
	@Column(name = "ngayketthucdangky")
	private Date ngayketthucdangky;
	
	@ManyToMany(targetEntity = LichHoc.class)
	@JoinTable(
		name = "ChiTiet",
		joinColumns = @JoinColumn(name = "madot"),
		inverseJoinColumns = @JoinColumn(name = "mathongtin")
	)
	private Set<LichHoc> DanhSachHocPhan;
	
	
	public DotDangKyHocPhan() {}
	
	public static void main(String[] args) {

	}

}
