package tw.brad.spring3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring3.dto.CustomerDto;
import tw.brad.spring3.dto.OrderDetailDto;
import tw.brad.spring3.dto.OrderDto;
import tw.brad.spring3.entity.Customer;
import tw.brad.spring3.entity.Order;
import tw.brad.spring3.entity.OrderDetail;
import tw.brad.spring3.repo.CustomerRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/v1/{id}")
    public ResponseEntity<Customer> test1(@PathVariable String id) {
        return ResponseEntity.ok(customerRepo.findById(id).orElse(null));
    }

    @GetMapping("/v2/{id}")
    public ResponseEntity<Customer> test2(@PathVariable String id) {
//        Optional<Customer> opt = customerRepo.findByCustomerID(id);
//        if (opt.isPresent()) {
//            Customer customer = opt.get();
//        }

        Customer customer = customerRepo.findByCustomerID(id).orElse(null);

//        customerRepo.findByCustomerID(id).orElse(new Customer());
//        customerRepo.findByCustomerID(id).orElseGet(Customer::new);
//        customerRepo.findByCustomerID(id).orElseThrow(
//                () -> new IllegalArgumentException("xxx"));

        return ResponseEntity.ok(customer);
    }


    @GetMapping("/v3/{id}")
    public ResponseEntity<CustomerDto> test3(@PathVariable String id) {
        Customer c = customerRepo.findById(id).orElse(null);
        List<Order> orders = c.getOrders();

        ArrayList<OrderDto> orderList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderDetail> details = order.getOrderDetails();

            ArrayList<OrderDetailDto> detailDtos = new ArrayList<>();
            for (OrderDetail detail : details) {
                detailDtos.add(new OrderDetailDto(
                        detail.getUnitPrice(),
                        detail.getQuantity(),
                        detail.getProduct().getProductName()));
            }

            orderList.add(new OrderDto(
                    order.getOrderid(), order.getOrderdate(), detailDtos));
        }
        //---------------------------
        CustomerDto cDto = new CustomerDto(
                c.getCustomerid(), c.getCompanyName(), orderList);

        return ResponseEntity.ok(cDto);
    }
}