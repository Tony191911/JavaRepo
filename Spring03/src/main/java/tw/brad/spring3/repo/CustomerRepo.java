package tw.brad.spring3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tw.brad.spring3.entity.Customer;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    @Query("""
            SELECT c
            FROM Customer c
            WHERE c.customerid = :id
            """)
    Optional<Customer> findByCustomerID(@Param("id") String id);
}
