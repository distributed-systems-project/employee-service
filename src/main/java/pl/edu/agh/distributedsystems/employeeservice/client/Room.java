package pl.edu.agh.distributedsystems.employeeservice.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
public class Room {

    private Long id;
    private int roomNumber;
    private int floorNumber;
    private double area;
    private RoomType roomType;
    private Building building;

    protected Room() {
    }

    public enum RoomType {
        SINGLE,
        DOUBLE,
        TRIPLE,
        QUAD,
        TWIN,
        STUDIO
    }
}
