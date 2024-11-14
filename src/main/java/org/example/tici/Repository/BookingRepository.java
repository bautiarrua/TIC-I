package org.example.tici.Repository;

import org.example.tici.Model.Entities.Booking;
import org.example.tici.Model.Entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findByFunction(Function function);
    boolean existsBookingByBookingId(int bookingId);
    void deleteBookingByBookingId(int bookingId);

    @Query("SELECT b FROM Booking b WHERE b.userId.mail = :mail")
    List<Booking> findBookingsByUserMail(@Param("mail") String mail);
}
