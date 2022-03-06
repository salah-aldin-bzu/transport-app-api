package com.bzu.transport_api.controllers;

import com.bzu.transport_api.models.Driver;
import com.bzu.transport_api.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin("*")

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/all")
    public ResponseEntity<Driver> getDrivers(){
        List<Driver> drivers = driverRepository.findAll();
        return new ResponseEntity(drivers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable int id){
        Optional<Driver> driver = driverRepository.findById(id);
        return new ResponseEntity(driver, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Driver> createDriver(@Validated @RequestBody Driver driver){
        driver.setUserType("Driver");
        driverRepository.save(driver);
        return new ResponseEntity(driver, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable int id){
        driverRepository.deleteById(id);
        return ResponseEntity.ok("Driver Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable int id, @Validated @RequestBody Driver newDriver){
        Optional<Driver> driverOptional = Optional.of(driverRepository.findById(id).map(driver -> {
            driver.setId(id);
            driver.setUserType("Driver");
            driver.setFirstName(newDriver.getFirstName());
            driver.setLastName(newDriver.getLastName());
            driver.setGender(newDriver.getGender());
            driver.setMobileNumber(newDriver.getMobileNumber());
            driver.setAddress(newDriver.getAddress());
            driver.setImagePath(newDriver.getImagePath());
            return driver;
        }).orElseGet(() -> {
            return driverRepository.save(newDriver);
        }));

        return new ResponseEntity(driverOptional, HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> updateDriverPassword(@RequestParam(name = "mobileNumber") String mobileNumber, @RequestParam(name = "password") String password, @RequestParam(name = "newPassword") String newPassword){
        Optional<Driver> driverOptional = Optional.ofNullable(driverRepository.findDriverByLoginInfo(mobileNumber, password).map(driver -> {
            driver.setPassword(newPassword);
            return driverRepository.save(driver);
        }).orElseGet(() -> {
            return null;
        }));

        if(driverOptional == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok("User password changed");
    }

    @GetMapping("/login")
    public ResponseEntity<Driver> loginDriver(@RequestParam(name = "mobileNumber") String mobileNumber, @RequestParam(name = "password") String password){
        Optional<Driver> driver = driverRepository.findDriverByLoginInfo(mobileNumber,password);

        if(driver.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(driver, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Driver> searchDrivers(@RequestParam(name = "searchString") String searchString){
        searchString = searchString.toLowerCase();
        String[] searchArray = searchString.split(" ");

        List<Driver> drivers = null;

        for (String str : searchArray){
            List<Driver> tmpList = driverRepository.searchDrivers('%'+str+'%');

            if(drivers == null){
                drivers = tmpList;
            }else {
                for(Driver newDriver : tmpList){
                    if(!drivers.contains(newDriver))
                        tmpList.add(newDriver);
                }
            }
        }


        return new ResponseEntity(drivers, HttpStatus.OK);
    }
}