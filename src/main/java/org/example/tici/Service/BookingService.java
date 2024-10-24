package org.example.tici.Service;

import org.example.tici.Model.Entities.*;

import org.example.tici.Repository.BookingRepository;
import org.example.tici.Repository.FunctionRepository;
import org.example.tici.Repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private SeatsRepository seatsRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FunctionRepository functionRepository;

    public boolean bookSeat (int function, Users user, List<Integer> seatsToReserve){

        Function functionOptional = functionRepository.findByIdFun(function);
        if (functionOptional == null) {
            return false;
        }

        List<SeatBook> reservedSeats = seatsRepository.findByFunctionAndSeatNumberIn(functionOptional, seatsToReserve);

        if (!reservedSeats.isEmpty()) {
            return false;
        }

        Booking booking = new Booking();
        booking.setBranchId(booking.getFunction().getProjectionRoom().getBranch());
        booking.setFunction(functionRepository.findByIdFun(function));
        booking.setMovieTitle(booking.getFunction().getMovieTitle());
        booking.setProjectionRoom(booking.getFunction().getProjectionRoom());
        booking.setUserId(user);

        bookingRepository.save(booking);


        for (Integer seatNumber : seatsToReserve) {
            SeatBook seat = new SeatBook();


            SeatBookId seatBookId = new SeatBookId(
                    seatNumber,
                    booking.getBookingId(),
                    user.getIdUs(),
                    function,
                    functionOptional.getMovieTitle().getTitle(),
                    functionOptional.getProjectionRoom().getRoomNumber(),
                    functionOptional.getProjectionRoom().getBranch().getIdBran()
            );

            seat.setSeatNumber(seatNumber);
            seat.setBooking(booking);
            seat.setUser(user);
            seat.setFunction(functionOptional);
            seat.setMovie(functionOptional.getMovieTitle());
            seat.setProjectionRoom(functionOptional.getProjectionRoom());
            seat.setBranch(functionOptional.getProjectionRoom().getBranch());

            seatsRepository.save(seat);
        }

        return true;

    }
}
