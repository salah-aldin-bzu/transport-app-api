package com.bzu.transport_api.controllers;

import com.bzu.transport_api.models.Driver;
import com.bzu.transport_api.models.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")

@RestController
public class WrapperController {

    @Autowired
    private DriverController driverController;

    @Autowired
    private PassengerController passengerController;

    @GetMapping("/login")
    public ResponseEntity loginUser(@RequestParam(name = "mobileNumber") String mobileNumber, @RequestParam(name = "password") String password){

        ResponseEntity driverResponseEntity = driverController.loginDriver(mobileNumber, password);
        ResponseEntity passengerResponseEntity = passengerController.loginPassenger(mobileNumber,password);

        if(driverResponseEntity.getStatusCodeValue() == 200)
            return driverResponseEntity;
        if(passengerResponseEntity.getStatusCodeValue() == 200)
            return passengerResponseEntity;

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
