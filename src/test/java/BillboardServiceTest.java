import org.example.tici.Exceptions.LimiteAlcanzado;
import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Billboard;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Repository.BillboardRepository;
import org.example.tici.Repository.BranchesRepository;
import org.example.tici.Repository.MovieRepository;
import org.example.tici.Service.BillboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
public class BillboardServiceTest {

    @Mock
    private BillboardRepository billboardRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private BranchesRepository branchesRepository;

    @InjectMocks
    private BillboardService billboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBillboard_Success() throws YaExiste, NoExiste {
        Billboard newBillboard = new Billboard(1, new Branches(1, "Central", 5));

        when(billboardRepository.findByIdBill(newBillboard.getIdBill())).thenReturn(null);
        when(branchesRepository.findByIdBran(newBillboard.getBranchId().getIdBran()))
                .thenReturn(newBillboard.getBranchId());
        when(billboardRepository.save(newBillboard)).thenReturn(newBillboard);

        Billboard addedBillboard = billboardService.addBillboard(newBillboard);

        assertNotNull(addedBillboard);
        verify(billboardRepository, times(1)).save(newBillboard);
    }

    @Test
    void testAddBillboard_ThrowsYaExiste() {
        Billboard existingBillboard = new Billboard(1, new Branches(1, "Central", 5));

        when(billboardRepository.findByIdBill(existingBillboard.getIdBill())).thenReturn(existingBillboard);

        assertThrows(YaExiste.class, () -> {
            billboardService.addBillboard(existingBillboard);
        });

        verify(billboardRepository, never()).save(existingBillboard);
    }

//    @Test
//    void testAddMovieToBillboard_Success() throws NoExiste, YaExiste {
//        Movie movie = new Movie("Test Movie", "Description", "Action", "English", 120, "Feature", "imageUrl");
//        Billboard billboard = new Billboard(1, new Branches(1, "Downtown", 5));
//
//        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(movie);
//        when(billboardRepository.findByIdBill(billboard.getIdBill())).thenReturn(billboard);
//        when(billboardRepository.save(billboard)).thenReturn(billboard);
//
//        Billboard updatedBillboard = billboardService.addMovieToBillboard(movie.getTitle(), billboard.getIdBill());
//
//        assertNotNull(updatedBillboard);
//        assertTrue(updatedBillboard.getMovies().contains(movie));
//        verify(billboardRepository, times(1)).save(billboard);
//    }

    @Test
    void testAddMovieToBillboard_ThrowsNoExisteForMovie() {
        when(movieRepository.findByTitle("NonExistentMovie")).thenReturn(null);

        assertThrows(NoExiste.class, () -> {
            billboardService.addMovieToBillboard("NonExistentMovie", 1);
        });

        verify(billboardRepository, never()).save(any(Billboard.class));
    }


//    @Test
//    void testAddMovieToBillboard_ThrowsNoExisteForBillboard() {
//        Movie movie = new Movie("Test Movie", "Description", "Action", "English", 120, "Feature", "imageUrl");
//
//        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(movie);
//        when(billboardRepository.findByIdBill(1)).thenReturn(null);
//
//        assertThrows(NoExiste.class, () -> {
//            billboardService.addMovieToBillboard(movie.getTitle(), 1);
//        });
//
//        verify(billboardRepository, never()).save(any(Billboard.class));
//    }

//    @Test
//    void testAddMovieToBillboard_ThrowsYaExiste() throws NoExiste {
//        Movie movie = new Movie("Test Movie", "Description", "Action", "English", 120, "Feature", "imageUrl");
//        Billboard billboard = new Billboard(1, new Branches(1, "Downtown", 5));
//
//        billboard.getMovies().add(movie);
//
//        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(movie);
//        when(billboardRepository.findByIdBill(billboard.getIdBill())).thenReturn(billboard);
//
//        assertThrows(YaExiste.class, () -> {
//            billboardService.addMovieToBillboard(movie.getTitle(), billboard.getIdBill());
//        });
//
//        verify(billboardRepository, never()).save(billboard);
//    }


    @Test
    void testGetBillboardByBranchId_Success() throws NoExiste {
        Billboard billboard = new Billboard(1, new Branches(1, "Downtown", 5));

        when(billboardRepository.findByIdBill(1)).thenReturn(billboard);
        when(billboardRepository.findByBranchId_IdBran(1)).thenReturn(billboard);

        Billboard foundBillboard = billboardService.getBillboardByBranchId(1);

        assertEquals(billboard, foundBillboard);
    }

    @Test
    void testGetBillboardByBranchId_ThrowsNoExisteForBillboardId() {
        when(billboardRepository.findByIdBill(1)).thenReturn(null);

        assertThrows(NoExiste.class, () -> {
            billboardService.getBillboardByBranchId(1);
        });

        verify(billboardRepository, never()).findByBranchId_IdBran(1);
    }

    @Test
    void testGetBillboardByBranchId_ThrowsNoExisteForBranchId() {
        when(billboardRepository.findByIdBill(1)).thenReturn(new Billboard());
        when(billboardRepository.findByBranchId_IdBran(1)).thenReturn(null);

        assertThrows(NoExiste.class, () -> {
            billboardService.getBillboardByBranchId(1);
        });
    }

    @Test
    void testAddBillboard_ThrowsNoExisteForNonexistentBranch() {
        Billboard newBillboard = new Billboard(1, new Branches(99, "Nonexistent", 5)); // branch ID no existe

        when(billboardRepository.findByIdBill(newBillboard.getIdBill())).thenReturn(null);
        when(branchesRepository.findByIdBran(newBillboard.getBranchId().getIdBran())).thenReturn(null);

        assertThrows(NoExiste.class, () -> {
            billboardService.addBillboard(newBillboard);
        });
    }

    @Test
    void testGetBillboardByBranchId_ThrowsNoExisteForNonexistentBranch() {
        int nonexistentBranchId = 99;

        lenient().when(billboardRepository.findByBranchId_IdBran(nonexistentBranchId)).thenReturn(null);

        assertThrows(NoExiste.class, () -> {
            billboardService.getBillboardByBranchId(nonexistentBranchId);
        });
    }

//    @Test
//    void testGetFilteredMovies_ReturnsFilteredMovies() {
//        int branchId = 1;
//        String category = "Action";
//        String language = "English";
//        String format = "Feature";
//
//        List<Movie> expectedMovies = List.of(
//                new Movie("Movie1", "Description1", category, language, 120, format, "imageUrl1"),
//                new Movie("Movie2", "Description2", category, language, 90, format, "imageUrl2")
//        );
//
//        when(movieRepository.findFilteredMovies(branchId, category, language, format)).thenReturn(expectedMovies);
//
//        List<Movie> filteredMovies = billboardService.getFilteredMovies(branchId, category, language, format);
//
//        assertEquals(expectedMovies, filteredMovies);
//    }

}
