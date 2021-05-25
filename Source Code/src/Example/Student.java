package Example;

import java.io.*;
import javax.persistence.*;

class StudentPK implements Serializable{
    private int Id;
    private String mssv;
    
    public StudentPK(){
        
    }
    
    public StudentPK(int id, String ms){
        this.Id = id;
        this.mssv = ms;
    }
}

@Entity
@Table(name = "student")
@IdClass(StudentPK.class)
public class Student implements Serializable{
    @Id
    @Column(name = "id")
    private int Id;
    @Id
    @Column(name = "mssv", length = 10)
    private String mssv;
    @Column(name = "hoten", length = 50)
    private String Name;
    @Column(name = "address", length = 30)
    private String Address;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
}
