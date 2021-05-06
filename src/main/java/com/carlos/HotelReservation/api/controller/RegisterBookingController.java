package com.carlos.HotelReservation.api.controller;

import com.carlos.HotelReservation.api.dto.RequestBookingDto;
import com.carlos.HotelReservation.api.handler.StandardException;
import com.carlos.HotelReservation.domain.repository.GuestRepository;
import com.carlos.HotelReservation.domain.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/bookings")
public class RegisterBookingController {

    private final Logger log = LoggerFactory.getLogger(RegisterBookingController.class);
    private final BookingService bookingService;
    private final GuestRepository guestRepository;

    public RegisterBookingController(BookingService bookingService, GuestRepository guestRepository) {
        this.bookingService = bookingService;
        this.guestRepository = guestRepository;
    }

    @PostMapping
    public ResponseEntity<?> registerBooking(@Valid @RequestBody RequestBookingDto request, UriComponentsBuilder uri){
        var guest = guestRepository.findById(request.getGuestId());
        if (guest.isEmpty()){
            log.warn("The user {} was not found in the database", request.getGuestId());
            return ResponseEntity.badRequest().body(new StandardException(Collections.singletonList("User not found")));
        }
        var booking = request.toEntity(guest.get());

        log.info("Starting scheduling process");
        return bookingService.processBooking(booking, uri);

    }
}
