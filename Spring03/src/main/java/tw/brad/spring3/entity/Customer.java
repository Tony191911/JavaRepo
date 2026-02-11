package tw.brad.spring3.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @Column(name = "CustomerID")
    private String customerid;

    @Column(name = "CompanyName")
    private String companyName;

    //------------------------
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;


}
