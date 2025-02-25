package com.hrs.reservation_task.repository;

import com.hrs.reservation_task.model.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelBookingJpaRepository extends JpaRepository<HotelBooking, Long> {
}
