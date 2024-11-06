package org.example.tici.Repository;

import org.example.tici.Model.Entities.Booking;
import org.example.tici.Model.Entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findByFunction(Function function);
    boolean existsBookingByBookingId(int bookingId);
    void deleteBookingByBookingId(int bookingId);
}
