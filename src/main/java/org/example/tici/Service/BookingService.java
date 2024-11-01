package org.example.tici.Service;

import org.example.tici.Model.Entities.*;

import org.example.tici.Repository.BookingRepository;
import org.example.tici.Repository.FunctionRepository;
import org.example.tici.Repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private SeatsRepository seatsRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FunctionRepository functionRepository;

    public boolean bookSeat(int functionId, Users user, List<Integer> seatsToReserve) {
        // Fetch the function and handle absence gracefully
        Optional<Function> functionOptionalOpt = Optional.ofNullable(functionRepository.findByIdFun(functionId));
        if (functionOptionalOpt.isEmpty()) {
            return false;
        }

        Function functionOptional = functionOptionalOpt.get();

        // Check if any of the seats are already reserved
        List<SeatBook> reservedSeats = seatsRepository.findByFunctionAndSeatNumberIn(functionOptional, seatsToReserve);
        if (!reservedSeats.isEmpty()) {
            return false;
        }

        // Create a new booking
        Booking booking = new Booking();
        booking.setBranchId(functionOptional.getProjectionRoom().getBranch());
        booking.setFunction(functionOptional);
        booking.setMovieTitle(functionOptional.getMovie());
        booking.setProjectionRoom(functionOptional.getProjectionRoom());
        booking.setUserId(user);
        booking = bookingRepository.save(booking);  // Save and get the booking ID

        // Final reference to booking for use in lambda
        final Booking finalBooking = booking;

        // Reserve each seat
        seatsToReserve.forEach(seatNumber -> {
            SeatBook seat = new SeatBook();

            // Create a composite ID for the seat booking
            SeatBookId seatBookId = new SeatBookId(
                    seatNumber,
                    finalBooking.getBookingId(),
                    user.getIdUs(),
                    functionId,
                    functionOptional.getMovie().getTitle(),
                    functionOptional.getProjectionRoom().getRoomNumber(),
                    functionOptional.getProjectionRoom().getBranch().getIdBran()
            );

            // Set properties for the seat booking
            seat.setSeatNumber(seatNumber);
            seat.setBooking(finalBooking);
            seat.setUser(user);
            seat.setFunction(functionOptional);
            seat.setMovie(functionOptional.getMovie());
            seat.setProjectionRoom(functionOptional.getProjectionRoom());
            seat.setBranch(functionOptional.getProjectionRoom().getBranch());

            // Save the seat booking
            seatsRepository.save(seat);
        });

        return true;
    }


}
