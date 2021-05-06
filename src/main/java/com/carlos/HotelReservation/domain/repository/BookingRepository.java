package com.carlos.HotelReservation.domain.repository;

import com.carlos.HotelReservation.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
      List<Booking> findAllByCheckInBetween(LocalDate start, LocalDate end);
      List<Booking> findAllByCheckOutBetween(LocalDate start, LocalDate end);
      List<Booking> findAllByCheckInBeforeAndCheckOutAfter(LocalDate start, LocalDate end);

      List<Booking> findByCheckInBeforeAndCheckOutAfter(LocalDate startDate, LocalDate endDate);

      default List<Booking> findByCheckInBeforeAndCheckOutAfter(LocalDate givenDate) {
            return findByCheckInBeforeAndCheckOutAfter(givenDate, givenDate);
      }
}
