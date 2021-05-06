package com.carlos.HotelReservation.integration.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.carlos.HotelReservation.factories.dto.RequestGuestDtoFactory.requestCreateGuest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase
public class RegisterGuestControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String URL_BASE = "/guests";

    @Test
    public void should_create_guest_and_return_201(){
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(URL_BASE, requestCreateGuest(), String.class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
    }
}
