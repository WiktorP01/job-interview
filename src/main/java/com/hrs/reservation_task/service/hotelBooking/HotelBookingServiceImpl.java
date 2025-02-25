package com.hrs.reservation_task.service.hotelBooking;

import com.hrs.reservation_task.dto.HotelBookingDto;
import com.hrs.reservation_task.exception.HotelBookingNotFoundException;
import com.hrs.reservation_task.model.HotelBooking;
import com.hrs.reservation_task.repository.HotelBookingJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelBookingServiceImpl implements HotelBookingService {

    private final HotelBookingJpaRepository hotelBookingJpaRepository;

    @Override
    @Transactional
    public HotelBookingDto save(HotelBookingDto hotelBookingDto) {
        return HotelBookingMapper.mapToDto(hotelBookingJpaRepository.save(HotelBookingMapper.mapToEntity(hotelBookingDto)));
    }

    @Transactional
    @Override
    public HotelBookingDto update(long id, HotelBookingDto hotelBookingDto) {
        HotelBooking existingHotelBooking = hotelBookingJpaRepository.findById(id)
                .orElseThrow(HotelBookingNotFoundException::new);
        existingHotelBooking.setGuestFirstName(hotelBookingDto.getGuestFirstName());
        existingHotelBooking.setGuestLastName(hotelBookingDto.getGuestLastName());
        existingHotelBooking.setStart(hotelBookingDto.getStart());
        existingHotelBooking.setEnd(hotelBookingDto.getEnd());
        return HotelBookingMapper.mapToDto(hotelBookingJpaRepository.save(existingHotelBooking));
    }

    @Override
    public HotelBookingDto getBooking(long id) {
        return hotelBookingJpaRepository.findById(id)
                .map(HotelBookingMapper::mapToDto)
                .orElseThrow(HotelBookingNotFoundException::new);
    }

    @Override
    public List<HotelBookingDto> getAll() {
        return hotelBookingJpaRepository.findAll()
                .stream()
                .map(HotelBookingMapper::mapToDto)
                .toList();
    }

    @Transactional
    @Override
    public void cancelBooking(long id) {
        var existingHotelBooking = hotelBookingJpaRepository.findById(id)
                .orElseThrow(HotelBookingNotFoundException::new);
        existingHotelBooking.setDeleted(true);
        hotelBookingJpaRepository.save(existingHotelBooking);
    }
}
