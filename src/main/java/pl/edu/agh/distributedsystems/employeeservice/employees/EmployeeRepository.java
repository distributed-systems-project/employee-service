package pl.edu.agh.distributedsystems.employeeservice.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeId(String login);
    List<Employee> findAllByHotelId(Long hotelId);
}
