package tw.brad.spring3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brad.spring3.entity.Employee;
import tw.brad.spring3.projection.EmployeeProjection;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<EmployeeProjection> searchByTitleStartingWith(String start);
}
