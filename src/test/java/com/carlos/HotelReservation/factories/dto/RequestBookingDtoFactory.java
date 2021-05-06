package com.carlos.HotelReservation.factories.dto;

import com.carlos.HotelReservation.api.dto.RequestBookingDto;
import com.carlos.HotelReservation.api.dto.RequestEditBookingDto;
import com.carlos.HotelReservation.domain.entity.Guest;
import com.carlos.HotelReservation.domain.repository.GuestRepository;

import java.time.LocalDate;

public class RequestBookingDtoFactory {

    public static RequestBookingDto requestBookingDayOneCheckInOne(GuestRepository repository){
        var guest = new Guest("test1@alten.com","123");
        repository.save(guest);
        return new RequestBookingDto(guest.getId(), LocalDate.now().plusDays(1), 1);
    }

    public static RequestBookingDto requestBookingDayTwoCheckInThree(GuestRepository repository){
        var guest = new Guest("test2@alten.com","123");
        repository.save(guest);
        return new RequestBookingDto(guest.getId(), LocalDate.now().plusDays(2), 3);
    }

    public static RequestBookingDto requestBookingDayThreeCheckInOne(GuestRepository repository){
        var guest = new Guest("test3@alten.com","123");
        repository.save(guest);
        return new RequestBookingDto(guest.getId(), LocalDate.now().plusDays(3), 1);
    }

    public static RequestBookingDto requestBookingDayTenCheckInOne(GuestRepository repository){
        var guest = new Guest("test4@alten.com","123");
        repository.save(guest);
        return new RequestBookingDto(guest.getId(), LocalDate.now().plusDays(10), 1);
    }

    public static RequestBookingDto requestBookingDayThirtyCheckInOne(GuestRepository repository){
        var guest = new Guest("test5@alten.com","123");
        repository.save(guest);
        return new RequestBookingDto(guest.getId(), LocalDate.now().plusDays(31), 1);
    }

    public static RequestEditBookingDto requestEditBookingDto(){

        return new RequestEditBookingDto(LocalDate.now().plusDays(3),2);
    }
}
