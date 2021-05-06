package com.carlos.HotelReservation.api.dto;

import com.carlos.HotelReservation.domain.entity.Guest;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ResponseGuestDto {
    public UUID guestId;

    public ResponseGuestDto(Guest guest) {
        this.guestId = guest.getId();
    }
}
