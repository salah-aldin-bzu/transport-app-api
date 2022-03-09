package com.bzu.transport_api.repositories;

import com.bzu.transport_api.models.Driver;
import com.bzu.transport_api.models.Passenger;
import com.bzu.transport_api.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query("SELECT r from Request r where r.driver=:driver and r.passenger=:passenger and status='pending'")
    Optional<Request> findPendingRequest(@Param("driver") Driver driver, @Param("passenger") Passenger passenger);

    @Query("SELECT r from Request r where r.driver=:driver and status='pending'")
    List<Request> findDriverPendingRequests(@Param("driver") Driver driver);

    @Query("Select r from Request r where r.driver=:driver")
    List<Request> findDriverRequests(@Param("driver") Driver driver);

    @Query("Select r from Request r where r.passenger=:passenger")
    List<Request> findPassengerRequests(@Param("passenger") Passenger passenger);
}
