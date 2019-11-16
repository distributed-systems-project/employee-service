package pl.edu.agh.distributedsystems.employeeservice.employees;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Employees")
@Data
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
    private String position;

    protected Employee() {
    }

    public Employee(Long hotelId, String name, int age, String position) {
        this.hotelId = hotelId;
        this.name = name;
        this.age = age;
        this.position = position;
    }
}
