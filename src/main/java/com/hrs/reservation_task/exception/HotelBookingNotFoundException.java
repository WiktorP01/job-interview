package com.hrs.reservation_task.exception;

public class HotelBookingNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Hotel booking not found.";

    public HotelBookingNotFoundException() {
        super(MESSAGE);
    }

}
