package com.bzu.transport_api.repositories;

import com.bzu.transport_api.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    @Query("SELECT p from Passenger p where p.mobileNumber=:mobileNumber and p.password=:password")
    Optional<Passenger> findPassengerByLoginInfo(@Param("mobileNumber") String mobileNumber, @Param("password") String password);

}
