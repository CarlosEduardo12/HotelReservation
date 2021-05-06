package com.carlos.HotelReservation.api.controller;

import com.carlos.HotelReservation.api.dto.RequestGuestDto;
import com.carlos.HotelReservation.api.dto.ResponseGuestDto;
import com.carlos.HotelReservation.domain.repository.GuestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/guests")
public class RegisterGuestController {

    private final Logger log = LoggerFactory.getLogger(RegisterGuestController.class);
    private final GuestRepository repository;

    public RegisterGuestController(GuestRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<ResponseGuestDto> registerUser(@Valid @RequestBody RequestGuestDto request){
        log.info("Registering new guest");
        var guest = request.toEntity();

        repository.save(guest);
        log.info("Registered successfully");
        return ResponseEntity.status(201).body(new ResponseGuestDto(guest));
    }
}
