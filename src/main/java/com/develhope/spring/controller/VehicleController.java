package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veicolo")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    //rotta creazione veicolo OK
    @PostMapping("/add")
    public ResponseEntity<?> createVehicle(@Validated @RequestBody CreateVehicleRequest createVehicleRequest) {
        VehicleDTO result = vehicleService.createVehicle(createVehicleRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    //rotta cancellazione veicolo da id OK
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        return vehicleService.deleteVehicle(id);
    }

    //rotta ricerca veicoli da marca OK
    @GetMapping("/getbymarca/{marca}")
    public ResponseEntity<?> getByMarca(@PathVariable String marca) {
        List<Vehicle> vehicles = vehicleService.searchByMarca(marca);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

}
