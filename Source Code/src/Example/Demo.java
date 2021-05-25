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
