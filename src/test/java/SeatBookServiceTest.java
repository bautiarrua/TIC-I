import org.example.tici.Model.Entities.Function;
import org.example.tici.Repository.SeatBookRepository;
import org.example.tici.Service.SeatBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import java.util.Collections;


@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
public class SeatBookServiceTest {

    @InjectMocks
    private SeatBookService seatBookService;

    @Mock
    private SeatBookRepository seatBookRepository;

    @Mock
    private Function function;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void testGetReservedSeatNumbersByFunction_ShouldReturnReservedSeats() {
//        List<Integer> reservedSeats = Arrays.asList(1, 2, 3, 4, 5);
//
//        when(seatBookRepository.findReservedSeatNumbersByFunction(any(Function.class)))
//                .thenReturn(reservedSeats);
//
//        List<Integer> result = seatBookService.getReservedSeatNumbersByFunction(function);
//
//        verify(seatBookRepository).findReservedSeatNumbersByFunction(function);
//
//        assertNotNull(result);
//        assertEquals(5, result.size());
//        assertTrue(result.containsAll(Arrays.asList(1, 2, 3, 4, 5)));
//    }

    @Test
    void testGetReservedSeatNumbersByFunction_WhenNoSeatsReserved_ShouldReturnEmptyList() {
        lenient().when(seatBookRepository.findReservedSeatNumbersByFunction(function)).thenReturn(Collections.emptyList());

        List<Integer> result = seatBookService.getReservedSeatNumbersByFunction(function);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}