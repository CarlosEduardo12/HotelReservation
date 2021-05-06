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
class ConsultBookingControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GuestRepository guestRepository;

    private final String URL_BASE = "/bookings";

    @Test
    public void should_list_all_booking_next_30_days_and_return_200(){
        restTemplate.postForEntity(URL_BASE, requestBookingDayThreeCheckInOne(guestRepository), String.class);
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(URL_BASE, String.class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void should_list_booking_by_id_and_return_200(){
        ResponseEntity<?> booking = restTemplate.postForEntity(URL_BASE, requestBookingDayTenCheckInOne(guestRepository), String.class);
        String [] headers = Objects.requireNonNull(booking.getHeaders().get("Location")).get(0).split("/");
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(URL_BASE+"/"+ headers[4], String.class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    }

}
