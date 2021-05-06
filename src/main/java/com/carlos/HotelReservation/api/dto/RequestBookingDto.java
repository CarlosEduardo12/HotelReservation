package com.carlos.HotelReservation.api.dto;

import com.carlos.HotelReservation.domain.entity.Booking;
import com.carlos.HotelReservation.domain.entity.Guest;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
public class RequestBookingDto {

    @NotNull
    private UUID guestId;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate checkIn;

    @Min(value = 1, message = "Your stay has to be at least 1 day")
    @Max(value = 3, message = "Your stay cannot exceed 3 days")
    private int stayDays;

    public RequestBookingDto(UUID guestId, LocalDate checkIn, int stayDays) {
        this.guestId = guestId;
        this.checkIn = checkIn;
        this.stayDays = stayDays;
    }

    public Booking toEntity(Guest guest){
        return new Booking(this.checkIn, this.stayDays, guest);
    }
}
