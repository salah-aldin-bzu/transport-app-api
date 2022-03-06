package com.bzu.transport_api.controllers;

import com.bzu.transport_api.models.Vehicle;
import com.bzu.transport_api.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("all")
    public ResponseEntity<Vehicle> getVehicles(){
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return new ResponseEntity(vehicles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable int id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return new ResponseEntity(vehicle, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Vehicle> createVehicle(@Validated @RequestBody Vehicle vehicle){
        vehicleRepository.save(vehicle);
        return new ResponseEntity(vehicle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable int id){
        vehicleRepository.deleteById(id);
        return ResponseEntity.ok("Vehicle Deleted");
    }

}
