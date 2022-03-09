package com.bzu.transport_api.controllers;

import com.bzu.transport_api.models.Bookmark;
import com.bzu.transport_api.models.Passenger;
import com.bzu.transport_api.models.Request;
import com.bzu.transport_api.repositories.DriverRepository;
import com.bzu.transport_api.repositories.PassengerRepository;
import com.bzu.transport_api.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping("/all")
    public ResponseEntity<Bookmark> getRequests(){
        List<Request> bookmarks = requestRepository.findAll();
        return new ResponseEntity(bookmarks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getRequest(@PathVariable int id){
        Optional<Request> bookmark = requestRepository.findById(id);
        return new ResponseEntity(bookmark, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Request> createRequest(@RequestParam(name = "driverID") int driverID, @RequestParam(name = "passengerID") int passengerID){

        if(requestRepository.findPendingRequest(driverRepository.getById(driverID), passengerRepository.getById(passengerID)).isEmpty()){
            Request request = new Request();

            request.setDriver(driverRepository.getById(driverID));
            request.setPassenger(passengerRepository.getById(passengerID));
            request.setStatus("pending");

            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            Date date = new Date();
            request.setDate(dateFormat.format(date));

            requestRepository.save(request);

            return new ResponseEntity(request,HttpStatus.OK);
        }else {
            return new ResponseEntity(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/driver/pending/{driverID}")
    public ResponseEntity<Request> getDriverPendingRequests(@PathVariable int driverID){
        List<Request> requests = requestRepository.findDriverPendingRequests(driverRepository.findById(driverID).get());
        return new ResponseEntity(requests, HttpStatus.OK);
    }

    @GetMapping("/driver/all/{driverID}")
    public ResponseEntity<Request> getDriverRequests(@PathVariable int driverID){
        List<Request> requests = requestRepository.findDriverRequests(driverRepository.findById(driverID).get());
        return new ResponseEntity(requests, HttpStatus.OK);
    }

    @GetMapping("/passenger/all/{passengerID}")
    public ResponseEntity<Request> getPassengerRequests(@PathVariable int passengerID){
        List<Request> requests = requestRepository.findPassengerRequests(passengerRepository.findById(passengerID).get());
        return new ResponseEntity(requests, HttpStatus.OK);
    }


    @PutMapping("/driver/accept")
    public ResponseEntity<String> driverAcceptRequest(@RequestParam(name = "driverID") int driverID, @RequestParam(name = "passengerID") int passengerID){
        Optional<Request> request = requestRepository.findPendingRequest(driverRepository.findById(driverID).get(), passengerRepository.findById(passengerID).get());

        if(request.get().getStatus().equals("pending")){
            request.get().setStatus("accepted");
            requestRepository.save(request.get());
        }

        return new ResponseEntity(request.get().getStatus(), HttpStatus.OK);
    }

    @PutMapping("/driver/reject")
    public ResponseEntity<String> driverRejectRequest(@RequestParam("driverID") int driverID, @RequestParam(name = "passengerID") int passengerID){
        Optional<Request> request = requestRepository.findPendingRequest(driverRepository.findById(driverID).get(), passengerRepository.findById(passengerID).get());

        if(request.get().getStatus().equals("pending")){
            request.get().setStatus("rejected");
            requestRepository.save(request.get());
        }

        return new ResponseEntity(request.get().getStatus(), HttpStatus.OK);
    }



/*
    @GetMapping("/testtime")
    public ResponseEntity<String> testTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return new ResponseEntity(dateFormat.format(date),HttpStatus.OK);
    }
 */
}
