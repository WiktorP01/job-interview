package com.hrs.reservation_task.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class HotelBookingDto {

    private long id;
    private String guestFirstName;
    private String guestLastName;
    private LocalDate checkIN;
    private LocalDate checkOut;

}
