package com.carlos.HotelReservation.domain.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;


@Entity
@Getter @Setter
public class Booking {

    @Id @GeneratedValue(generator = "uuid4")
    private UUID id;

    @ManyToOne
    private Guest guest;

    @Future
    private LocalDate checkIn;

    @Future
    private LocalDate checkOut;

    @Min(value = 1, message = "Your stay has to be at least 1 day")
    @Max(value = 3, message = "Your stay cannot exceed 3 days")
    private int stayDays;

    @Deprecated
    public Booking(){}

    public Booking(LocalDate checkIn, int stayDays, Guest guest){
        this.checkIn = checkIn;
        this.checkOut = checkIn.plusDays(stayDays-1);
        this.stayDays = stayDays;
        this.guest = guest;
    }

    public Boolean isAfterMaxOfAdvanceDays(int maxOfDays){
        Date currentDate = new Date();
        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localDate = localDate.plusDays(maxOfDays);
        return this.checkIn.isAfter(localDate);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "guest=" + guest +
                ", checkIn=" + checkIn +
                ", stayDays=" + stayDays +
                '}';
    }
}
