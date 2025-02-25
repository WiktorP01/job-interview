package com.hrs.reservation_task.service.hotelBooking;

import com.hrs.reservation_task.dto.HotelBookingDto;

import java.util.List;

public interface HotelBookingService {

    HotelBookingDto save(HotelBookingDto hotelBookingDto);

    HotelBookingDto update(long id, HotelBookingDto hotelBookingDto);

    HotelBookingDto getBooking(long id);

    List<HotelBookingDto> getAll();

    void cancelBooking(long id);

}
