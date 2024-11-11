package org.example.tici.Repository;

import org.example.tici.Model.Entities.Booking;
import org.example.tici.Model.Entities.Function;
import org.example.tici.Model.Entities.SeatBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<SeatBook, Integer> {
    List<SeatBook> findByFunctionAndSeatNumberIn(Function function, List<Integer> seatNumbers);
    List<SeatBook> findByBookingAndSeatNumberIn (Booking booking, List<Integer> seatNumbers);
    List<SeatBook> findByBooking(Booking booking);
    void deleteByBooking(Booking booking);
}
