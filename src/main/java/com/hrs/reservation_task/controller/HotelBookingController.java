package com.hrs.reservation_task.controller;

import com.hrs.reservation_task.dto.HotelBookingDto;
import com.hrs.reservation_task.service.hotelBooking.HotelBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel-bookings")
@Tag(name = "Hotel Booking API", description = "Endpoints for managing hotel bookings")
@RequiredArgsConstructor
public class HotelBookingController {

    private final HotelBookingService hotelBookingService;

    @Operation(
            summary = "Create a new hotel booking",
            description = "Creates a booking with customer name, check-in date, and check-out date.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Booking created successfully")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid request format"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<HotelBookingDto> createBooking(@RequestBody HotelBookingDto booking) {
        HotelBookingDto savedBooking = hotelBookingService.save(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all bookings", description = "Retrieves a list of all hotel bookings.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval")
    @GetMapping
    public ResponseEntity<List<HotelBookingDto>> getAllBookings() {
        return ResponseEntity.ok(hotelBookingService.getAll());
    }

    @Operation(summary = "Get a booking by ID", description = "Retrieves a specific booking by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HotelBookingDto> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelBookingService.getBooking(id));
    }

    @Operation(summary = "Update a booking", description = "Updates the details of an existing booking.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HotelBookingDto> updateBooking(@PathVariable Long id, @RequestBody HotelBookingDto booking) {
        return ResponseEntity.ok(hotelBookingService.update(id, booking));
    }

    @Operation(summary = "Cancel a booking", description = "Marks a booking as canceled (soft delete).")
    @ApiResponse(responseCode = "200", description = "Booking canceled successfully")
    @ApiResponse(responseCode = "404", description = "Booking not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        hotelBookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking canceled successfully.");
    }
}
