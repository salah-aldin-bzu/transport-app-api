package com.bzu.transport_api.repositories;

import com.bzu.transport_api.models.Driver;
import com.bzu.transport_api.models.Location;
import com.bzu.transport_api.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    // Query code to get all drivers locations
    @Query("SELECT l from Location l where l.passenger is not null ")
    List<Location> getPassengersLocations();

    // Query code to get all passengers locations
    @Query("SELECT l from Location l where l.driver is not null")
    List<Location> getDriversLocations();

}