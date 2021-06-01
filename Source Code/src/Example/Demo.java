package Example;

import POJO.*;

import java.util.*;
import org.hibernate.query.Query;
import org.hibernate.*;
//import org.hibernate.cfg.*;

public class Demo {
    public static void main(String args[]){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();

//            Query<Student> query = session.createQuery("FROM Student");
//            List<Student> st = query.list();
//            st.forEach(c -> System.out.println(c.getName()));
//            session.close();
            
//            Query<LichHoc> query = session.createQuery("FROM LichHoc");
//            List<LichHoc> st = query.list();
//            st.forEach(c -> System.out.println(c.toString()));
//            session.close();
            
//            HocKyPK pk = new HocKyPK(2020, "HK1");
//            HocKy a = session.get(HocKy.class, pk);
//            Set<LichHoc> temp = a.getThongTinLichHoc();
//            
//            System.out.println(temp.size());
//            
//            ArrayList <LichHoc> dslh = new ArrayList<LichHoc>(temp.size());
//            for(LichHoc x : temp) {
//            	dslh.add(x);
//            }         
//            System.out.println(dslh.get(0).toString());
            
            LichHoc lh = session.get(LichHoc.class, 1);
            Set<SinhVien> svs = lh.getDanhSachSinhVien();
            for(SinhVien x : svs) {
            	System.out.println(x.toString());
            }
            
//            LopHoc lh = session.get(LopHoc.class, "19CTT3");
//            Set<SinhVien> svs = lh.getDanhSachSinhVien();
//            for(SinhVien x : svs) {
//            	System.out.println(x.toString());
//            }
        }
        catch(HibernateException e){
            System.out.println(e.getMessage());
        }
    }
}

class Person{
	protected String name;
	protected String address;
	
	public Person() {}
	
	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}	
	public String toString() {
		return this.name + " - " + this.address;
	}
}


class Student extends Person{
	private String program;
	private int year;
	private double fee;
	
	public Student() {}
	
	public Student(String name, String address, String program, int year, double fee) {
		super(name, address);
		this.program = program;
		this.year = year;
		this.fee = fee;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public String toString() {
		return super.toString() + " - " + this.program + " - " +this.year + " - " +this.fee;
	}
}

class Staff extends Person{
	private String school;
	private double pay;
	public Staff(String name, String address, String school, double pay) {
		super(name, address);
		this.school = school;
		this.pay = pay;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
	public String toString() {
		return super.toString() + " - " + this.school + " - " +this.pay;
	}
	
}
