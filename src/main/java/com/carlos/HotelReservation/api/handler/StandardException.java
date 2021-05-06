package com.carlos.HotelReservation.api.handler;

import java.util.Collection;

public class StandardException {

    private final Collection<String> messages;

    public StandardException(Collection<String> messages) {
        this.messages = messages;
    }

    public Collection<String> getMessages() {
        return messages;
    }
}
