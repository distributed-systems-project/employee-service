package pl.edu.agh.distributedsystems.employeeservice.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.distributedsystems.employeeservice.employees.Employee;
import pl.edu.agh.distributedsystems.employeeservice.employees.EmployeeRepository;

import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
public class LoginController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public LoginController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String[] credentials = getCredentials(authHeader);
        Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(credentials[0]);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            if (employee.getPassword().equals(credentials[1])) {
                return ResponseEntity
                        .ok(employee.toPrincipal());
            }
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\":\"Invalid password\"}");
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("{\"message\":\"Employee with given number not exist.\"}");
    }

    private String[] getCredentials(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String encodedCredentials = authHeader.substring(6);
        String decodedCredentials = new String(Base64Utils.decodeFromString(encodedCredentials), UTF_8);
        return decodedCredentials.split(":");
    }
}
