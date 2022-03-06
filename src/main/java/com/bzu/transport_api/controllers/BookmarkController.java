package com.bzu.transport_api.controllers;

import com.bzu.transport_api.models.Bookmark;
import com.bzu.transport_api.models.Driver;
import com.bzu.transport_api.repositories.BookmarkRepository;
import com.bzu.transport_api.repositories.DriverRepository;
import com.bzu.transport_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping("/all")
    public ResponseEntity<Bookmark> getBookmarks(){
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        return new ResponseEntity(bookmarks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getBookmark(@PathVariable int id){
        Optional<Bookmark> bookmark = bookmarkRepository.findById(id);
        return new ResponseEntity(bookmark, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Bookmark> createBookmark(@RequestParam(name = "driverID") int driverID, @RequestParam(name = "passengerID") int passengerID){

        if(bookmarkRepository.findBookmarkByDriverAndPassenger(driverRepository.findById(driverID).get(),passengerRepository.findById(passengerID).get()).isEmpty()){
            Bookmark bookmark = new Bookmark();

            bookmark.setDriver(driverRepository.getById(driverID));
            bookmark.setPassenger(passengerRepository.getById(passengerID));

            bookmarkRepository.save(bookmark);
            return new ResponseEntity(bookmark, HttpStatus.OK);
        }else {
            return new ResponseEntity(null, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBookmark(@RequestParam(name = "driverID") int driverID, @RequestParam(name = "passengerID") int passengerID){

        Optional<Bookmark> bookmark = bookmarkRepository.findBookmarkByDriverAndPassenger(driverRepository.findById(driverID).get(),passengerRepository.findById(passengerID).get());

        if(bookmark.isEmpty()){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }else {
            bookmarkRepository.deleteById(bookmark.get().getId());
            return ResponseEntity.ok("Bookmark Deleted");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Driver> getPassengerBookmarks(@PathVariable int id){
        List<Bookmark> bookmark = passengerRepository.findById(id).get().getBookmarks();

        List<Driver> drivers = new ArrayList<>();
        bookmark.forEach(bookmark1 -> {
            drivers.add(bookmark1.getDriver());
        });

        return new ResponseEntity(drivers, HttpStatus.OK);
    }
}
