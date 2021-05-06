package com.carlos.HotelReservation.api.dto;

import com.carlos.HotelReservation.domain.entity.Booking;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
public class RequestEditBookingDto {

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate checkIn;

    @Min(value = 1, message = "Your stay has to be at least 1 day")
    @Max(value = 3, message = "Your stay cannot exceed 3 days")
    private int stayDays;

    public RequestEditBookingDto(LocalDate checkIn, int stayDays) {
        this.checkIn = checkIn;
        this.stayDays = stayDays;
    }

    public Booking toEntity(Booking booking){
        booking.setCheckIn(this.checkIn);
        booking.setStayDays(this.stayDays);
        booking.setCheckOut(this.checkIn.plusDays(this.stayDays-1));
        return booking;
    }

}
