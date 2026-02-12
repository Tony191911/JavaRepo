package tw.brad.spring3.projection;

import tw.brad.spring3.entity.Order;

import java.util.List;

/*
 * 方法名稱 -> Entity
 */
public interface EmployeeProjection {
//    String getLastName();
//    String getFirstName();
    String getTitle();
    List<Order> getOrders();
}
