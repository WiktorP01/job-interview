package com.hrs.reservation_task.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hrs.reservation_task.dto.HotelBookingDto;
import com.hrs.reservation_task.service.hotelBooking.HotelBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HotelBookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HotelBookingService hotelBookingService;

    @InjectMocks
    private HotelBookingController hotelBookingController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(hotelBookingController).build();
    }

    @Test
    void testCreateBooking() throws Exception {
        HotelBookingDto booking = new HotelBookingDto(1L, "John","Doe", LocalDate.of(2025,2,2), LocalDate.of(2025,2,10));
        when(hotelBookingService.save(any(HotelBookingDto.class))).thenReturn(booking);

        mockMvc.perform(post("/api/hotel-bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    void testGetAllBookings() throws Exception {
        List<HotelBookingDto> bookings = Arrays.asList(
                new HotelBookingDto(1L, "John","Doe", LocalDate.of(2025,2,2), LocalDate.of(2025,2,10)),
                new HotelBookingDto(2L, "John","Doe2", LocalDate.of(2025,2,3), LocalDate.of(2025,2,10))
        );
        when(hotelBookingService.getAll()).thenReturn(bookings);

        mockMvc.perform(get("/api/hotel-bookings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetBookingById() throws Exception {
        HotelBookingDto booking = new HotelBookingDto(1L, "John","Doe", LocalDate.of(2025,2,2), LocalDate.of(2025,2,10));
        when(hotelBookingService.getBooking(1L)).thenReturn(booking);

        mockMvc.perform(get("/api/hotel-bookings/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    void testUpdateBooking() throws Exception {
        HotelBookingDto updatedBooking = new HotelBookingDto(1L, "John","Doe", LocalDate.of(2025,2,2), LocalDate.of(2025,2,10));
        when(hotelBookingService.update(eq(1L), any(HotelBookingDto.class))).thenReturn(updatedBooking);

        mockMvc.perform(put("/api/hotel-bookings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBooking)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe Updated"));
    }

    @Test
    void testCancelBooking() throws Exception {
        doNothing().when(hotelBookingService).cancelBooking(1L);

        mockMvc.perform(delete("/api/hotel-bookings/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Booking canceled successfully."));
    }

}
