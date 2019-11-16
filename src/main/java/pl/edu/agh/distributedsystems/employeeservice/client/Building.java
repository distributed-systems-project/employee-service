package pl.edu.agh.distributedsystems.employeeservice.client;

import lombok.Data;

import java.util.List;


@Data
public class Building {

    private Long id;

    private String name;
    private Address address;
    private int numOfFloors;
    private double floorArea;
    private String description;

    protected Building() {
    }

    public Building(String name, int numOfFloors) {
        this.name = name;
        this.numOfFloors = numOfFloors;
    }
}
