package com.carlos.HotelReservation.factories.dto;

import com.carlos.HotelReservation.api.dto.RequestGuestDto;

public class RequestGuestDtoFactory {
    public static RequestGuestDto requestCreateGuest(){
        return new RequestGuestDto("carlos@alten.com","123456");
    }
}
