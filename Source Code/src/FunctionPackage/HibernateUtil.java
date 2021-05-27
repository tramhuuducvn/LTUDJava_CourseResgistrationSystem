package FunctionPackage;

import java.util.*;
import org.hibernate.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
import POJO.*;

public class HibernateUtil {
    private SessionFactory FACTORY;
    
    public HibernateUtil(String host, String port, String database, String user, String password) {
    	Configuration conf = new Configuration();
    	Properties properties = new Properties();
    	properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
    	properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
    	properties.put(Environment.URL, "jdbc:mysql://" + host + ":" + port + "/"+ database);
    	properties.put(Environment.USER, user);
    	properties.put(Environment.PASS, password);
    	
    	conf.setProperties(properties);
    	conf.addAnnotatedClass(GiaoVu.class);
    	conf.addAnnotatedClass(SinhVien.class);
    	conf.addAnnotatedClass(HocKy.class);
    	conf.addAnnotatedClass(LopHoc.class);
    	conf.addAnnotatedClass(MonHoc.class);
    	conf.addAnnotatedClass(LichHoc.class);
    	conf.addAnnotatedClass(DangKy.class);
    	conf.addAnnotatedClass(DotDangKyHocPhan.class);
    	conf.addAnnotatedClass(ChiTiet.class);
    	
    	ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
    	FACTORY = conf.buildSessionFactory(registry);
    }
    
    public SessionFactory getFACTORY() {
    	return FACTORY;
    }
}

