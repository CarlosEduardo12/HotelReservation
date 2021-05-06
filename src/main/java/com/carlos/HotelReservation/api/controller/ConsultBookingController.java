package com.carlos.HotelReservation.api.controller;

import com.carlos.HotelReservation.api.handler.StandardException;
import com.carlos.HotelReservation.domain.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/bookings")
public class ConsultBookingController {

    private final Logger log = LoggerFactory.getLogger(ConsultBookingController.class);
    private final BookingRepository repository;

    public ConsultBookingController(BookingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultReservation(@PathVariable UUID id){
        log.info("consulting a reservation");
        var book = repository.findById(id);
        if (book.isEmpty()){
            log.warn("Booking with id: {} not found", id);
            return ResponseEntity.badRequest().body(new StandardException(Collections.singletonList("Booking date not found")));
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<?> availableRooms(){
        log.info("consult rooms available in the next 30 days");
        Date currentDate = new Date();
        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        var listOfAvailableDays =  repository.findAllByCheckInBetween(localDate, localDate.plusDays(30));
        return ResponseEntity.ok(listOfAvailableDays);
    }
}
