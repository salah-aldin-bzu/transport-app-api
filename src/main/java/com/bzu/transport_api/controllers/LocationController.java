package com.bzu.transport_api.controllers;

import com.bzu.transport_api.models.Location;
import com.bzu.transport_api.models.Request;
import com.bzu.transport_api.repositories.DriverRepository;
import com.bzu.transport_api.repositories.LocationRepository;
import com.bzu.transport_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/all")
    public ResponseEntity<Location> getLocations(){
        List<Location> locations = locationRepository.findAll();
        return new ResponseEntity(locations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable int id){
        Optional<Location> location = locationRepository.findById(id);
        return new ResponseEntity(location, HttpStatus.OK);
    }

    @PostMapping("/passenger/send")
    public ResponseEntity<Location> createPassengerLocation(@RequestParam("passengerID") int passengerID, @RequestParam(name = "latitude") String latitude, @RequestParam(name = "longitude") String longitude){
        Location location = passengerRepository.findById(passengerID).get().getLocation();

        if (location == null){
            location = new Location();

            location.setPassenger(passengerRepository.findById(passengerID).get());
            location.setDriver(null);
        }

        location.setLatitude(latitude);
        location.setLongitude(longitude);

        locationRepository.save(location);

        return new ResponseEntity(location, HttpStatus.OK);
    }

    @PostMapping("/driver/send")
    public ResponseEntity<Location> createDriverLocation(@RequestParam("driverID") int driverID, @RequestParam(name = "latitude") String latitude, @RequestParam(name = "longitude") String longitude) {
        Location location = driverRepository.findById(driverID).get().getLocation();

        if (location == null) {
            location = new Location();

            location.setPassenger(null);
            location.setDriver(driverRepository.findById(driverID).get());
        }

        location.setLatitude(latitude);
        location.setLongitude(longitude);

        locationRepository.save(location);

        return new ResponseEntity(location, HttpStatus.OK);
    }

    @GetMapping("/passenger/all")
    public ResponseEntity<Location> getPassengersLocations(){
        List<Location> locations = locationRepository.getPassengersLocations();
        return new ResponseEntity(locations, HttpStatus.OK);
    }

    @GetMapping("/driver/all")
    public ResponseEntity<Location> getDriversLocations(){
        List<Location> locations = locationRepository.getDriversLocations();
        return new ResponseEntity(locations, HttpStatus.OK);
    }
}
