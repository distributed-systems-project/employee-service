package pl.edu.agh.distributedsystems.employeeservice.employees;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.distributedsystems.employeeservice.security.EmployeePrincipal;

import javax.persistence.*;

@Entity
@Table(name = "Employees")
@Getter
@Setter
@EqualsAndHashCode
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long hotelId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String employeeId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String position;

    protected Employee() {
    }

    public Employee(Long hotelId, String name, int age, String employeeId, String password, String position) {
        this.hotelId = hotelId;
        this.name = name;
        this.age = age;
        this.employeeId = employeeId;
        this.password = password;
        this.position = position;
    }

    public EmployeePrincipal toPrincipal() {
        return new EmployeePrincipal(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", employeeId='" + employeeId + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
