
import static org.junit.jupiter.api.Assertions.*;

import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Repository.MovieRepository;
import org.example.tici.Service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("La Invitada", "Descripción", "Terror", "Español", 85, "2D", "http://image.url", "http://trailer.url", "+15");
    }

    @Test
    void testAddMovie_WhenMovieDoesNotExist_ShouldAddMovie() throws YaExiste {
        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(null);

        Movie result = movieService.addMovie(movie);

        verify(movieRepository).save(movie);
        assertEquals(movie.getTitle(), result.getTitle());
    }

    @Test
    void testAddMovie_WhenMovieAlreadyExists_ShouldThrowYaExiste() {
        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(movie);

        assertThrows(YaExiste.class, () -> movieService.addMovie(movie));
    }

    @Test
    void testFindByTitle_WhenMovieExists_ShouldReturnMovie() {
        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(movie);

        Movie result = movieService.findByTitle(movie.getTitle());

        assertNotNull(result);
        assertEquals(movie.getTitle(), result.getTitle());
    }

    @Test
    void testFindByTitle_WhenMovieDoesNotExist_ShouldReturnNull() {
        when(movieRepository.findByTitle(movie.getTitle())).thenReturn(null);

        Movie result = movieService.findByTitle(movie.getTitle());

        assertNull(result);
    }

}

