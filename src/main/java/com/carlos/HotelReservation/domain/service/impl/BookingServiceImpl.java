package com.carlos.HotelReservation.domain.service.impl;

import com.carlos.HotelReservation.api.handler.StandardException;
import com.carlos.HotelReservation.domain.entity.Booking;
import com.carlos.HotelReservation.domain.repository.BookingRepository;
import com.carlos.HotelReservation.domain.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final BookingRepository repository;

    public BookingServiceImpl(BookingRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<?> processBooking(Booking booking, UriComponentsBuilder uri){
        log.info("Checking the availability of the booking");

        if (conflictingDates(booking)){
            return ResponseEntity.badRequest().body(new StandardException(Collections.singletonList("booking date not available")));
        }

        if (booking.isAfterMaxOfAdvanceDays(30)){
            log.warn("Limit of 30 days in advance exceeded");
            return ResponseEntity.badRequest().body(new StandardException(Collections.singletonList("Limit of 30 days in advance exceeded")));
        }

        repository.save(booking);
        log.info("Booking successful");
        return ResponseEntity.created(uri.path("/booking/{id}").buildAndExpand(booking.getId()).toUri()).build();
    }

    private boolean conflictingDates(Booking booking){
        List<Booking> listOfCheckIn = repository.findAllByCheckInBetween(booking.getCheckIn(), booking.getCheckOut());
        List<Booking> listOfCheckOut = repository.findAllByCheckOutBetween(booking.getCheckIn(), booking.getCheckOut());
        List<Booking> listOfBlockedDays = repository.findAllByCheckInBeforeAndCheckOutAfter(booking.getCheckIn(), booking.getCheckOut());
        if (booking.getId() != null){
            listOfCheckIn.remove(booking);
            listOfCheckOut.remove(booking);
            listOfBlockedDays.remove(booking);
        }
        if (!listOfCheckIn.isEmpty() || !listOfCheckOut.isEmpty() || !listOfBlockedDays.isEmpty()){
            log.warn("Conflicting dates");
            return true;
        }
        return false;
    }
}
