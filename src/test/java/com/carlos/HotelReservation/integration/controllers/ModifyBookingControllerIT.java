package com.carlos.HotelReservation.integration.controllers;

import com.carlos.HotelReservation.domain.repository.GuestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static com.carlos.HotelReservation.factories.dto.RequestBookingDtoFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase
public class ModifyBookingControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private GuestRepository guestRepository;

    private final String URL_BASE = "/bookings";

    @Test
    public void should_update_booking_and_return_201(){
        var booking = restTemplate.postForEntity(URL_BASE, requestBookingDayThreeCheckInOne(guestRepository), String.class);
        String [] headers = Objects.requireNonNull(booking.getHeaders().get("Location")).get(0).split("/");
        ResponseEntity<?> bookingBeforeUpdate = restTemplate.getForEntity(URL_BASE+"/"+ headers[4], String.class);

        restTemplate.patchForObject(URL_BASE+"/"+ headers[4], requestEditBookingDto(), String.class);

        ResponseEntity<?> bookingAfterUpdate = restTemplate.getForEntity(URL_BASE+"/"+ headers[4], String.class);

        assertThat(bookingBeforeUpdate).isNotEqualTo(bookingAfterUpdate);
    }

    @Test
    public void should_delete_booking_and_return_204(){
        var booking = restTemplate.postForEntity(URL_BASE, requestBookingDayThreeCheckInOne(guestRepository), String.class);
        String [] headers = Objects.requireNonNull(booking.getHeaders().get("Location")).get(0).split("/");

        restTemplate.delete(URL_BASE+"/"+ headers[4], String.class);

        ResponseEntity<?> responseEntity = restTemplate.getForEntity(URL_BASE+"/"+ headers[4], String.class);
        assertThat(booking.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

}
