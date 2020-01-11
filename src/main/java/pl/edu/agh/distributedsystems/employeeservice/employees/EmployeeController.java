package pl.edu.agh.distributedsystems.employeeservice.employees;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.distributedsystems.employeeservice.client.Building;
import pl.edu.agh.distributedsystems.employeeservice.client.BuildingClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService service;
    private BuildingClient buildingClient;

    @Autowired
    public EmployeeController(EmployeeService service,
                              BuildingClient buildingClient) {
        this.service = service;
        this.buildingClient = buildingClient;
    }

    @GetMapping
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return service.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        Employee addedEmployee = service.addEmployee(employee);
        return ResponseEntity.created(URI.create("/employee/employees/" + addedEmployee.getId())).build();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return service.findById(id);
    }

    @GetMapping("/hotels/{hotelId}")
    public List<EmployeeWithHotel> findByHotel(@PathVariable("hotelId") Long hotelId) {
        LOGGER.info("Employee find: hotelId={}", hotelId);
        Building hotel = buildingClient.findByHotel(hotelId);
        List<Employee> employees = service.findByHotelId(hotelId);

        ArrayList<EmployeeWithHotel> employeesWithHotel = new ArrayList<>();
        for (Employee e : employees) {
            employeesWithHotel.add(new EmployeeWithHotel(hotel, e));
        }
        return employeesWithHotel;
    }

    @Data
    public static class EmployeeWithHotel {
        private Building hotel;
        private Employee employee;

        public EmployeeWithHotel(Building hotel, Employee employee) {
            this.hotel = hotel;
            this.employee = employee;
        }
    }
}
