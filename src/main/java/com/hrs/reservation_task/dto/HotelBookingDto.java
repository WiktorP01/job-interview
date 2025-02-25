package com.hrs.reservation_task.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class HotelBookingDto {

    private long id;
    private String guestFirstName;
    private String guestLastName;
    private LocalDate start;
    private LocalDate end;

}
