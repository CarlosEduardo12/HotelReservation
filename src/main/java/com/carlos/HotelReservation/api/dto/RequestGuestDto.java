package com.carlos.HotelReservation.api.dto;

import com.carlos.HotelReservation.api.handler.Unique;
import com.carlos.HotelReservation.domain.entity.Guest;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RequestGuestDto {

    @Email @Getter
    @Unique(domainClass = Guest.class, fieldName = "email")
    public String email;

    @NotBlank
    public String password;

    public RequestGuestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Guest toEntity() {
        return new Guest(this.email, this.password);
    }
}
