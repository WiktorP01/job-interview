package com.hrs.reservation_task.repository;

import com.hrs.reservation_task.model.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelBookingJpaRepository extends JpaRepository<HotelBooking, Long> {

    @Override
    @Query(" SELECT hb FROM HotelBooing hb WHERE hb.deleted = false ")
    List<HotelBooking> findAll();

    @Override
    @Query(" SELECT hb FROM HotelBooking hb WHERE hb.id=:id AND hb.deleted = false ")
    Optional<HotelBooking> findById(@Param("id") Long id);

}
