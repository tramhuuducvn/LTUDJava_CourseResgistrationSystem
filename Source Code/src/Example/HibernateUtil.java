package Example;

import java.util.*;
import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import POJO.*;


public class HibernateUtil {
    private final static SessionFactory FACTORY;
    static {
        Configuration conf = new Configuration();
        Properties pros = new Properties();
        pros.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        pros.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        pros.put(Environment.URL, "jdbc:mysql://localhost:3306/CourseRegistrationSystem");
        pros.put(Environment.USER, "tramhuuduc");
        pros.put(Environment.PASS, "19120484@Ubuntu");
                
        conf.setProperties(pros);
        conf.addAnnotatedClass(GiaoVu.class);
        conf.addAnnotatedClass(SinhVien.class);
        conf.addAnnotatedClass(HocKy.class);
        conf.addAnnotatedClass(LopHoc.class);
        conf.addAnnotatedClass(MonHoc.class);
        conf.addAnnotatedClass(LichHoc.class);
        conf.addAnnotatedClass(DangKy.class);
        conf.addAnnotatedClass(DotDangKyHocPhan.class);
        conf.addAnnotatedClass(ChiTiet.class);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                           .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }
    
    public static SessionFactory getSessionFactory(){
        return FACTORY;
    }
}
