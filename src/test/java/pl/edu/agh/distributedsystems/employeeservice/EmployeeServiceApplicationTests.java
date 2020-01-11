package pl.edu.agh.distributedsystems.employeeservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.distributedsystems.employeeservice.client.Building;
import pl.edu.agh.distributedsystems.employeeservice.client.BuildingClient;
import pl.edu.agh.distributedsystems.employeeservice.employees.Employee;
import pl.edu.agh.distributedsystems.employeeservice.employees.EmployeeController;
import pl.edu.agh.distributedsystems.employeeservice.employees.EmployeeController.EmployeeWithHotel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private BuildingClient buildingClient;

    private String getURL() {
        return "http://localhost:" + port + "/";
    }

    @Test
    public void testGetAll() {
        Employee[] result = restTemplate.getForObject(getURL() + "/employees", Employee[].class);
        assertThat(result.length).isGreaterThan(0);
    }

    @Test
    public void testGetOne() {
        Employee result = restTemplate.getForObject(getURL() + "/employees/1", Employee.class);
        assertThat(result.getName()).contains("John");
    }

    @Test
    public void testAddOne() {
        HttpEntity<Employee> request = new HttpEntity<>(new Employee(1L, "John Smith", 34, "EX1111", "1234", "Manager"));
        ResponseEntity<Employee> response = restTemplate
                .exchange(getURL() + "/employees", HttpMethod.POST, request, Employee.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Employee created = response.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getName()).isEqualTo("John");
    }

    @Test
    public void testFindByHotel() {
        when(buildingClient.findByHotel(6L)).thenReturn(new Building("ABC", 10));
        EmployeeWithHotel[] result =
                restTemplate.getForObject(getURL() + "/employees/hotels/6", EmployeeWithHotel[].class);
        assertThat(result.length).isEqualTo(1);
        assertThat(result[0].getEmployee().getName()).isEqualTo("Kevin Price");
        assertThat(result[0].getHotel().getName()).isEqualTo("ABC");
    }
}
