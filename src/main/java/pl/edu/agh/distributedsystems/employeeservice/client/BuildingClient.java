package pl.edu.agh.distributedsystems.employeeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "building-service")
public interface BuildingClient {

    @GetMapping("/buildings/{hotelId}")
    List<Building> findByHotel(@PathVariable("hotelId") Long hotelId);
}
