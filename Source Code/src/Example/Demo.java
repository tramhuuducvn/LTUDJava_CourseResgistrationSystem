package Example;

import java.util.*;
import org.hibernate.query.Query;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class Demo {
    public static void main(String args[]){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();

            Query<Student> query = session.createQuery("FROM Student");
            List<Student> st = query.list();
            st.forEach(c -> System.out.println(c.getName()));
            session.close();
//        	SessionFactory factory = new AnnotationConfiguration().configure("./configuration/hibernate.cfg.xml").
//        					addAnnotatedClass(Student.class).buildSessionFactory();
//        	Session session = factory.openSession();
//        	
//        	Transaction tx = session.beginTransaction();
//        	List students = session.createQuery("from student").list();
//        	
//        	Iterator i = students.iterator();
//        	while (i.hasNext()) {
//        		Student st = (Student)i.next();
//        		System.out.println(st.getName());
//        	}
//        	
//        	tx.commit();
        }
        catch(HibernateException e){
            System.out.println(e.getMessage());
        }
    }
}
