package pl.edu.agh.distributedsystems.employeeservice.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    Employee addEmployee(Employee employee) {
        System.out.println(employee);
        UUID uuid = UUID.randomUUID();
        employee.setEmployeeId(uuid.toString());
        employee.setPassword("password");
        System.out.println(employee);
        employeeRepository.save(employee);
        return employee;
    }

    Employee findById(Long employeeId) {
        if (employeeId <= 0) throw new IllegalArgumentException("Employee id must be number greater than zero");
        return employeeRepository.findById(employeeId).orElseThrow(NoSuchElementException::new);
    }

    void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> findByHotelId(Long hotelId) {
        return employeeRepository.findAllByHotelId(hotelId);
    }
}
