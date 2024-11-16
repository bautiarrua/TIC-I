import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.tici.Model.Entities.*;
import org.example.tici.Repository.BookingRepository;

import org.example.tici.Repository.FunctionRepository;
import org.example.tici.Repository.SeatsRepository;
import org.example.tici.Service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private SeatsRepository seatsRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FunctionRepository functionRepository;

    @InjectMocks
    private BookingService bookingService;

    private Users user;
    private Function function;
    private Movie movie;
    private ProjectionRoom projectionRoom;
    private Branches branch;
    private SeatBook seatBook;
    private Booking booking;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new Users();
        user.setIdUs(1);

        movie = new Movie("La Invitada", "descripcion", "Terror", "Español", 85, "2D", "https://movieprodportalstoweb.blob.core.windows.net/movieposters/lainvitada.jpg", "https://www.youtube.com/watch?v=qadegIcxaCQ", "+15");

        projectionRoom = new ProjectionRoom();
        projectionRoom.setRoomNumber(1);
        branch = new Branches();
        branch.setIdBran(1);
        projectionRoom.setBranch(branch);

        function = new Function();
        function.setIdFun(1);
        function.setMovie(movie);
        function.setProjectionRoom(projectionRoom);

        booking = new Booking(user, function, movie, projectionRoom, branch);
    }

    // Test de bookSeat
    @Test
    public void testBookSeat_success() {
        List<Integer> seatsToReserve = List.of(1, 2);

        when(functionRepository.findByIdFun(1)).thenReturn(function);
        when(seatsRepository.findByFunctionAndSeatNumberIn(function, seatsToReserve)).thenReturn(List.of());

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        boolean result = bookingService.bookSeat(1, user, seatsToReserve);

        assertTrue(result);

        verify(seatsRepository, times(2)).save(any(SeatBook.class));
    }

    @Test
    public void testBookSeat_functionNotFound() {
        when(functionRepository.findByIdFun(1)).thenReturn(null);

        List<Integer> seatsToReserve = List.of(1, 2);

        boolean result = bookingService.bookSeat(1, user, seatsToReserve);

        assertFalse(result);
    }

    @Test
    public void testCancelReserve_success() {
        List<Integer> seatsToCancel = List.of(1, 2);

        Booking booking = new Booking();

        SeatBook seatBook = new SeatBook();
        seatBook.setBooking(booking);
        seatBook.setSeatNumber(1);

        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        when(seatsRepository.findByBookingAndSeatNumberIn(booking, seatsToCancel))
                .thenReturn(List.of(seatBook));

        boolean result = bookingService.cancelReserve(1, seatsToCancel);

        assertTrue(result);

        verify(seatsRepository, times(1)).deleteAll(anyList());
    }

    @Test
    public void testCancelReserve_bookingNotFound() {
        List<Integer> seatsToCancel = List.of(1, 2);

        when(bookingRepository.findById(1)).thenReturn(Optional.empty());

        boolean result = bookingService.cancelReserve(1, seatsToCancel);

        assertFalse(result);
    }

    @Test
    public void testBookSeat_someSeatsAlreadyReserved() {
        List<Integer> seatsToReserve = List.of(1, 2);

        when(functionRepository.findByIdFun(1)).thenReturn(function);
        when(seatsRepository.findByFunctionAndSeatNumberIn(function, seatsToReserve))
                .thenReturn(List.of(new SeatBook()));

        boolean result = bookingService.bookSeat(1, user, seatsToReserve);

        assertFalse(result);

        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test
    public void testCancelReserve_noSeatsToCancel() {
        List<Integer> seatsToCancel = List.of(3, 4);

        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        when(seatsRepository.findByBookingAndSeatNumberIn(booking, seatsToCancel)).thenReturn(List.of());

        boolean result = bookingService.cancelReserve(1, seatsToCancel);

        assertFalse(result);
    }

    @Test
    public void testCancelReserve_deleteBookingIfLastSeat() {
        List<Integer> seatsToCancel = List.of(1);

        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        when(seatsRepository.findByBookingAndSeatNumberIn(booking, seatsToCancel)).thenReturn(List.of(new SeatBook()));
        when(seatsRepository.findByBooking(booking)).thenReturn(List.of());
        boolean result = bookingService.cancelReserve(1, seatsToCancel);

        assertTrue(result);
        verify(bookingRepository, times(1)).deleteBookingByBookingId(1);
    }

    @Test
    public void testBookSeat_saveMultipleSeats() {
        List<Integer> seatsToReserve = List.of(1, 2, 3);

        when(functionRepository.findByIdFun(1)).thenReturn(function);
        when(seatsRepository.findByFunctionAndSeatNumberIn(function, seatsToReserve)).thenReturn(List.of());
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        boolean result = bookingService.bookSeat(1, user, seatsToReserve);

        assertTrue(result);
        verify(seatsRepository, times(3)).save(any(SeatBook.class));
    }

}
