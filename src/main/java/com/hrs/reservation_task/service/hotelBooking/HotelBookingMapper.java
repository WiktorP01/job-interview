package com.hrs.reservation_task.service.hotelBooking;

import com.hrs.reservation_task.dto.HotelBookingDto;
import com.hrs.reservation_task.model.HotelBooking;

public class HotelBookingMapper {

    public static HotelBooking mapToEntity(HotelBookingDto hotelBookingDto) {
        return HotelBooking.builder()
                .guestFirstName(hotelBookingDto.getGuestFirstName())
                .guestLastName(hotelBookingDto.getGuestLastName())
                .checkIn(hotelBookingDto.getCheckIN())
                .checkOut(hotelBookingDto.getCheckOut())
                .build();
    }

    public static HotelBookingDto mapToDto(HotelBooking hotelBooking) {
        return HotelBookingDto.builder()
                .id(hotelBooking.getId())
                .guestFirstName(hotelBooking.getGuestFirstName())
                .guestLastName(hotelBooking.getGuestLastName())
                .checkIN(hotelBooking.getCheckIn())
                .checkOut(hotelBooking.getCheckOut())
                .build();
    }

}
