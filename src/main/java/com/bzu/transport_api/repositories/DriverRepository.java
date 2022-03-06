package com.bzu.transport_api.repositories;

import com.bzu.transport_api.models.Driver;
import com.bzu.transport_api.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    @Query("SELECT d from Driver d where d.mobileNumber=:mobileNumber and d.password=:password")
    Optional<Driver> findDriverByLoginInfo(@Param("mobileNumber") String mobileNumber, @Param("password") String password);

    @Query("SELECT d from Driver d where d.firstName like :searchString or d.lastName like :searchString or d.address like :searchString")
    List<Driver> searchDrivers(@Param("searchString") String searchString);

}
