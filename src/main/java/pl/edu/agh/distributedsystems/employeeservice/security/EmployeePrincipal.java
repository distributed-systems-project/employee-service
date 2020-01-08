package pl.edu.agh.distributedsystems.employeeservice.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.distributedsystems.employeeservice.employees.Employee;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeePrincipal {

    private String employeeId;
    private Long hotelId;
    private String position;

    public EmployeePrincipal(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.hotelId = employee.getHotelId();
        this.position = employee.getPosition();
    }

    @Override
    public String toString() {
        return "EmployeePrincipal{" +
                "employeeId='" + employeeId + '\'' +
                ", hotelId=" + hotelId +
                ", position='" + position + '\'' +
                '}';
    }
}
