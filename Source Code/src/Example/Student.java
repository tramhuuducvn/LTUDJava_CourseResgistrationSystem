package Example;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;
    @Column(name = "name", length = 32)
    private String Name;
    @Column(name = "address", length = 32)
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
}

