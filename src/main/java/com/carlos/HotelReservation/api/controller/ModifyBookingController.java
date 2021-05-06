package com.carlos.HotelReservation.api.controller;

import com.carlos.HotelReservation.api.dto.RequestEditBookingDto;
import com.carlos.HotelReservation.domain.repository.BookingRepository;
import com.carlos.HotelReservation.domain.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/bookings")
public class ModifyBookingController {

    private final Logger log = LoggerFactory.getLogger(ModifyBookingController.class);
    private final BookingRepository repository;
    private final BookingService bookingService;


    public ModifyBookingController(BookingRepository repository, BookingService bookingService) {
        this.repository = repository;
        this.bookingService = bookingService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> modifyBooking(@PathVariable UUID id, @Valid @RequestBody RequestEditBookingDto request, UriComponentsBuilder uri){
        log.info("Starting update process");
        var booking = repository.findById(id);
        if (booking.isEmpty()){
            log.warn("The booking id: {} was not found in the database", id);
            return ResponseEntity.badRequest().build();
        }
        var newBooking = request.toEntity(booking.get());
        return bookingService.processBooking(newBooking, uri);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id){
        log.info("Starting delete process");
        var booking = repository.findById(id);
        if (booking.isEmpty()){
            log.warn("The booking id: {} was not found in the database", id);
            return ResponseEntity.badRequest().build();
        }
        repository.delete(booking.get());
        log.info("Reservation successfully deleted");
        return ResponseEntity.noContent().build();
    }
}
