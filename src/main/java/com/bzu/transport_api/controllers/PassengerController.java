package com.bzu.transport_api.controllers;

import com.bzu.transport_api.models.Passenger;
import com.bzu.transport_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping("/all")
    public ResponseEntity<Passenger> getPassengers(){
        List<Passenger> passengers = passengerRepository.findAll();
        return new ResponseEntity(passengers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable int id){
        Optional<Passenger> passenger = passengerRepository.findById(id);
        return new ResponseEntity(passenger, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Passenger> createPassenger(@Validated @RequestBody Passenger passenger){
        passenger.setUserType("Passenger");
        passengerRepository.save(passenger);
        return new ResponseEntity(passenger, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDriver(@RequestParam(name = "mobileNumber") String mobileNumber, @RequestParam(name = "password") String password){
        Optional<Passenger> passenger = passengerRepository.findPassengerByLoginInfo(mobileNumber,password);

        if(passenger.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        passengerRepository.deleteById(passenger.get().getId());

        return ResponseEntity.ok("Passenger Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable int id,@Validated @RequestBody Passenger newPassenger){
        Optional<Passenger> passengerOptional = Optional.of(passengerRepository.findById(id).map(passenger -> {
            passenger.setId(id);
            passenger.setUserType("Passenger");
            passenger.setFirstName(newPassenger.getFirstName());
            passenger.setLastName(newPassenger.getLastName());
            passenger.setGender(newPassenger.getGender());
            passenger.setMobileNumber(newPassenger.getMobileNumber());
            passenger.setAddress(newPassenger.getAddress());
            passenger.setImagePath(newPassenger.getImagePath());
            return passengerRepository.save(passenger);
        }).orElseGet(() -> {
            return passengerRepository.save(newPassenger);
        }));
        return new ResponseEntity(passengerOptional,HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> updatePassengerPassword(@RequestParam(name = "mobileNumber") String mobileNumber, @RequestParam(name = "password") String password, @RequestParam(name = "newPassword") String newPassword){
        Optional<Passenger> passengerOptional = Optional.ofNullable(passengerRepository.findPassengerByLoginInfo(mobileNumber,password).map(passenger ->{
            passenger.setPassword(newPassword);
            return passengerRepository.save(passenger);
        }).orElseGet(()-> {
            return null;
        }));

        if(passengerOptional == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok("User password changed");
    }

    @GetMapping("/login")
    public ResponseEntity<Passenger> loginPassenger(@RequestParam(name = "mobileNumber") String mobileNumber, @RequestParam(name = "password") String password){
        Optional<Passenger> passenger = passengerRepository.findPassengerByLoginInfo(mobileNumber,password);

        if(passenger.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(passenger, HttpStatus.OK);
    }
}
