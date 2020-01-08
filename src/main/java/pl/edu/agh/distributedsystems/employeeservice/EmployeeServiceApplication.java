package pl.edu.agh.distributedsystems.employeeservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.distributedsystems.employeeservice.employees.Employee;
import pl.edu.agh.distributedsystems.employeeservice.employees.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDummyData(EmployeeRepository repository) {
        List<Employee> employees = createEmployees();

        return (args) -> {
            repository.saveAll(employees);
        };
    }

    private List<Employee> createEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John Smith", 34, "EX1111", "1234", "Manager"));
        employees.add(new Employee(2L, "Darren Hamilton", 37, "EX2222", "1234", "Manager"));
        employees.add(new Employee(3L, "Tom Scott", 26, "EX3333", "1234", "Manager"));
        employees.add(new Employee(4L, "Anna London", 39, "EX4444", "1234", "Manager"));
        employees.add(new Employee(5L, "Patrick Dempsey", 27, "EX5555", "1234", "Manager"));
        employees.add(new Employee(6L, "Kevin Price", 38, "EX6666", "1234", "Manager"));
        employees.add(new Employee(1L, "Ian Scott", 34, "EX7777", "1234", "Receptionist"));
        employees.add(new Employee(2L, "Andrew Campton", 30, "EX8888", "1234", "Receptionist"));
        employees.add(new Employee(3L, "Steve Franklin", 25, "EX9999", "1234", "Receptionist"));
        employees.add(new Employee(4L, "Elisabeth Smith", 30, "EX1112", "1234", "Receptionist"));
        return employees;
    }

}
