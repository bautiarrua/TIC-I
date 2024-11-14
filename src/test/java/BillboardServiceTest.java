
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

import java.util.ArrayList;
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

    private Billboard billboard;
    private Movie movie;
    private Branches branch;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        branch = new Branches();
        branch.setIdBran(1);

        billboard = new Billboard(1, branch);

        movie = new Movie("Inception", "A mind-bending thriller", "Sci-Fi", "English", 148, "2D", "image_url", "trailer_url", "PG-13");
    }

    @Test
    public void testAddBillboard_success() throws YaExiste, NoExiste {
        when(billboardRepository.findByIdBill(1)).thenReturn(null);
        when(branchesRepository.findByIdBran(1)).thenReturn(branch);
        when(billboardRepository.save(billboard)).thenReturn(billboard);

        Billboard result = billboardService.addBillboard(billboard);

        assertNotNull(result);
        verify(billboardRepository).save(billboard);
    }


    @Test
    public void testAddBillboard_alreadyExists() {
        when(billboardRepository.findByIdBill(1)).thenReturn(billboard);

        assertThrows(YaExiste.class, () -> {
            billboardService.addBillboard(billboard);
        });
    }

    @Test
    public void testAddMovieToBillboard_success() throws NoExiste, YaExiste {
        Movie movie = new Movie("Inception", "A mind-bending thriller", "Sci-Fi", "English", 148, "2D", "imageUrl", "trailerUrl", "PG-13");

        Branches branch = new Branches(1, "Downtown", 10);
        Billboard billboard = new Billboard(1, branch);

        when(movieRepository.findByTitle("Inception")).thenReturn(movie);
        when(billboardRepository.findByIdBill(1)).thenReturn(billboard);
        when(billboardRepository.save(billboard)).thenReturn(billboard);

        Billboard result = billboardService.addMovieToBillboard("Inception", 1);

        assertNotNull(result);
        assertTrue(result.getMovies().contains(movie));
        verify(billboardRepository).save(billboard);
    }


    @Test
    public void testAddMovieToBillboard_movieNotFound() {
        when(movieRepository.findByTitle("Inception")).thenReturn(null);

        assertThrows(NoExiste.class, () -> {
            billboardService.addMovieToBillboard("Inception", 1);
        });
    }

    @Test
    public void testAddMovieToBillboard_movieAlreadyAdded() throws NoExiste, YaExiste {
        when(movieRepository.findByTitle("Inception")).thenReturn(movie);
        when(billboardRepository.findByIdBill(1)).thenReturn(billboard);

        billboard.getMovies().add(movie);

        assertThrows(YaExiste.class, () -> {
            billboardService.addMovieToBillboard("Inception", 1);
        });
    }

    @Test
    public void testGetBillboardByBranchId_success() throws NoExiste {
        Branches branch = new Branches(1, "Downtown", 10);
        Billboard billboard = new Billboard(1, branch);

        when(billboardRepository.findByBranchId_IdBran(1)).thenReturn(billboard);

        Billboard result = billboardService.getBillboardByBranchId(1);

        assertNotNull(result);
        assertEquals(billboard, result);
    }

    @Test
    public void testGetBillboardByBranchId_notFound() {
        when(billboardRepository.findByBranchId_IdBran(1)).thenReturn(null);

        assertThrows(NoExiste.class, () -> {
            billboardService.getBillboardByBranchId(1);
        });
    }

    @Test
    public void testGetFilteredMovies_success() {
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);

        when(movieRepository.findFilteredMovies(1, "Sci-Fi", "English", "2D")).thenReturn(movies);

        List<Movie> result = billboardService.getFilteredMovies(1, "Sci-Fi", "English", "2D");

        assertNotNull(result);
        assertTrue(result.contains(movie));
    }
}
