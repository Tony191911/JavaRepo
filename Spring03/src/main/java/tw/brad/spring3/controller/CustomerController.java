package tw.brad.spring3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring3.entity.Customer;
import tw.brad.spring3.repo.CustomerRepo;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> test1(@PathVariable String id) {
        return ResponseEntity.ok(customerRepo.findById(id).orElse(null));
    }
}
