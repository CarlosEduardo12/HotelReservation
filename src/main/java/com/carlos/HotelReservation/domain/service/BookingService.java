package com.carlos.HotelReservation.domain.service;

import com.carlos.HotelReservation.domain.entity.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface BookingService {
    ResponseEntity<?> processBooking(Booking booking, UriComponentsBuilder uri);
}
