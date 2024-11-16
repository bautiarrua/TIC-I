package org.example.tici.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.tici.DTO.BookingResponseDTO;
import org.example.tici.Model.Entities.*;

import org.example.tici.Repository.BookingRepository;
import org.example.tici.Repository.FunctionRepository;
import org.example.tici.Repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private SeatsRepository seatsRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FunctionRepository functionRepository;

    public boolean bookSeat(int functionId, Users user, List<Integer> seatsToReserve) {
        Optional<Function> functionOptionalOpt = Optional.ofNullable(functionRepository.findByIdFun(functionId));
        if (functionOptionalOpt.isEmpty()) {
            return false;
        }

        Function functionOptional = functionOptionalOpt.get();


        List<SeatBook> reservedSeats = seatsRepository.findByFunctionAndSeatNumberIn(functionOptional, seatsToReserve);
        if (!reservedSeats.isEmpty()) {
            return false;
        }


        Booking booking = new Booking();
        booking.setBranchId(functionOptional.getProjectionRoom().getBranch());
        booking.setFunction(functionOptional);
        booking.setMovieTitle(functionOptional.getMovie());
        booking.setProjectionRoom(functionOptional.getProjectionRoom());
        booking.setUserId(user);
        booking = bookingRepository.save(booking);


        final Booking finalBooking = booking;

        seatsToReserve.forEach(seatNumber -> {
            SeatBook seat = new SeatBook();





            SeatBookId seatBookId = new SeatBookId(
                    seatNumber,
                    finalBooking.getBookingId(),
                    user.getIdUs(),
                    functionId,
                    functionOptional.getMovie().getTitle(),
                    functionOptional.getProjectionRoom().getRoomNumber(),
                    functionOptional.getProjectionRoom().getBranch().getIdBran()
            );



            seat.setSeatNumber(seatNumber);
            seat.setBooking(finalBooking);
            seat.setUser(user);
            seat.setFunction(functionOptional);
            seat.setMovie(functionOptional.getMovie());
            seat.setProjectionRoom(functionOptional.getProjectionRoom());
            seat.setBranch(functionOptional.getProjectionRoom().getBranch());
            seatsRepository.save(seat);
        });

        return true;
    }

    public boolean cancelReserve (int bookingId, List<Integer> seatsToCancel) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();

            List<SeatBook> seatsToBeCancelled = seatsRepository.findByBookingAndSeatNumberIn(booking, seatsToCancel);
            if (seatsToBeCancelled.isEmpty()) {
                return false;
            }
            seatsRepository.deleteAll(seatsToBeCancelled);

            if (seatsRepository.findByBooking(booking).isEmpty()){
                bookingRepository.deleteBookingByBookingId(bookingId);
            }
            return true;
        }
        return false;
    }

    public List<BookingResponseDTO> getBookingsByUserMail(String mail) {
        List<Booking> bookings = bookingRepository.findByUserEmail(mail);

        return bookings.stream()
                .map(booking -> new BookingResponseDTO(
                        booking.getBranchId().getNeighborhood(), // Barrio del cine
                        booking.getFunction().getStartTime(), // Hora de inicio
                        booking.getFunction().getEndTime(), // Hora de fin
                        booking.getMovieTitle().getTitle(), // Título de la película
                        booking.getSeats().stream()
                                .map(seat -> seat.getSeatNumber()) // Asientos reservados
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }


}
