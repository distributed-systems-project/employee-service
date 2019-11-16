package pl.edu.agh.distributedsystems.employeeservice.employees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return service.getAllEmployees();
    }

    @PostMapping
    public void add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        service.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return service.findById(id);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<Employee> findByHotel(@PathVariable("hotelId") Long hotelId) {
        LOGGER.info("Employee find: hotelId={}", hotelId);
        return service.findByHotelId(hotelId);
    }
}
