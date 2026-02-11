package tw.brad.spring3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @Column(name = "OrderID")
    private Integer orderid;

    @Column(name = "OrderDate")
    private Date orderdate;

    //------------------------
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    @JsonBackReference
    private Employee employee;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

}
