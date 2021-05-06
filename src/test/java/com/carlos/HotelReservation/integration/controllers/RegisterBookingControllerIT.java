package com.carlos.HotelReservation.integration.controllers;

import com.carlos.HotelReservation.domain.repository.GuestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.carlos.HotelReservation.factories.dto.RequestBookingDtoFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase
class RegisterBookingControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private GuestRepository guestRepository;

    private final String URL_BASE = "/bookings";

    @Test
    public void should_register_booking_and_return_201(){
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(URL_BASE, requestBookingDayOneCheckInOne(guestRepository), String.class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void should_conflicting_dates_for_same_day_checkIn_and_return_400(){
        restTemplate.postForEntity(URL_BASE, requestBookingDayTwoCheckInThree(guestRepository), String.class);
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(URL_BASE, requestBookingDayTwoCheckInThree(guestRepository), String.class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void should_conflicting_dates_for_booking_between_checkIn_and_checkOut_and_return_400(){
        restTemplate.postForEntity(URL_BASE, requestBookingDayTwoCheckInThree(guestRepository), String.class);
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(URL_BASE, requestBookingDayThreeCheckInOne(guestRepository), String.class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void should_not_booking_more_than_30_days_advance_and_return_400(){
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(URL_BASE, requestBookingDayThirtyCheckInOne(guestRepository), String.class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
