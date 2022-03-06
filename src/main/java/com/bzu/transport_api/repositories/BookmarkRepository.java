package com.bzu.transport_api.repositories;

import com.bzu.transport_api.models.Bookmark;
import com.bzu.transport_api.models.Driver;
import com.bzu.transport_api.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    @Query("SELECT b from Bookmark b where b.driver=:driver and b.passenger=:passenger")
    Optional<Bookmark> findBookmarkByDriverAndPassenger(@Param("driver") Driver driver, @Param("passenger") Passenger passeneger);

    @Query("SELECT b from Bookmark b where b.passenger=:passenger")
    List<Bookmark> findPassengerBookmarks(@Param("passenger") Passenger passenger);
}
