package com.hrs.reservation_task.service;

import com.hrs.reservation_task.dto.HotelBookingDto;

import java.util.List;

public interface HotelBookingService {

    void save(HotelBookingDto hotelBookingDto);

    HotelBookingDto getBooking(long id);

    List<HotelBookingDto> getAll();

    void cancelBooking(long id);

}
