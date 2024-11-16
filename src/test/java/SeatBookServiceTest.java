import org.example.tici.Service.*;

import org.example.tici.Model.Entities.Function;
import org.example.tici.Repository.SeatBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SeatBookServiceTest {

    @Mock
    private SeatBookRepository seatBookRepository;

    @InjectMocks
    private SeatBookService seatBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReservedSeatNumbersByFunction_ShouldReturnReservedSeats() {

        Function function = new Function();
        List<Integer> expectedReservedSeats = Arrays.asList(1, 2, 3);

        when(seatBookRepository.findReservedSeatNumbersByFunction(function)).thenReturn(expectedReservedSeats);

        List<Integer> reservedSeats = seatBookService.getReservedSeatNumbersByFunction(function);

        assertNotNull(reservedSeats, "Reserved seats should not be null");
        assertEquals(3, reservedSeats.size(), "The number of reserved seats should match");
        assertTrue(reservedSeats.contains(1), "Reserved seats should contain seat number 1");
        assertTrue(reservedSeats.contains(2), "Reserved seats should contain seat number 2");
        assertTrue(reservedSeats.contains(3), "Reserved seats should contain seat number 3");

        verify(seatBookRepository, times(1)).findReservedSeatNumbersByFunction(function);
    }

    @Test
    void getReservedSeatNumbersByFunction_ShouldReturnEmptyList_WhenNoSeatsReserved() {
        // Arrange
        Function function = new Function();
        List<Integer> expectedReservedSeats = Arrays.asList();

        when(seatBookRepository.findReservedSeatNumbersByFunction(function)).thenReturn(expectedReservedSeats);


        List<Integer> reservedSeats = seatBookService.getReservedSeatNumbersByFunction(function);


        assertNotNull(reservedSeats, "Reserved seats should not be null");
        assertTrue(reservedSeats.isEmpty(), "Reserved seats list should be empty");

        verify(seatBookRepository, times(1)).findReservedSeatNumbersByFunction(function);
    }
}

