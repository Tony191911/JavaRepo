package tw.brad.spring3.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @Column(name = "EmployeeID")
    private Integer employeeid;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "Title")
    private String title;

    //-------------------------
    @OneToMany(mappedBy = "employee")
    private List<Order> orders;
}
