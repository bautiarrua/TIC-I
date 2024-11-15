import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.tici.DTO.FunctionDTO;
import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Function;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Model.Entities.ProjectionRoom;
import org.example.tici.Repository.*;
import org.example.tici.Service.FunctionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
public class FunctionServiceTest {

    @InjectMocks
    private FunctionService functionService;

    @Mock
    private FunctionRepository functionRepository;

    @Mock
    private BranchesRepository branchesRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ProjectionRoomRepository projectionRoomRepository;

    private Movie movie;
    private Branches branch;
    private ProjectionRoom projectionRoom;
    private Function function;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

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
    }

    @Test
    void testGetFunctionsByBranchMovieAndDate() {
        int branchId = 1;
        String movieTitle = "La Invitada";
        int dayMonth = 1230;

        when(functionRepository.findByBranchMovieAndDate(branchId, movieTitle, dayMonth)).thenReturn(List.of(function));

        List<Function> result = functionService.getFunctionsByBranchMovieAndDate(branchId, movieTitle, dayMonth);

        assertFalse(result.isEmpty());
        verify(functionRepository).findByBranchMovieAndDate(branchId, movieTitle, dayMonth);
    }

    @Test
    void testAddFunction_Succes() throws NoExiste {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setMovieTitle("La Invitada");
        functionDTO.setProjectionRoomNumber(1);
        functionDTO.setBranchId(1);

        when(movieRepository.findByTitle("La Invitada")).thenReturn(movie);
        when(projectionRoomRepository.findByRoomNumber(1)).thenReturn(projectionRoom);
        when(branchesRepository.findByIdBran(1)).thenReturn(branch);
        when(functionRepository.save(any(Function.class))).thenReturn(function);

        Function result = functionService.addFunction(functionDTO);

        assertNotNull(result);
        verify(functionRepository).save(any(Function.class));
    }

    @Test
    void testAddFunction_WhenMovieNotFound_ShouldThrowNoExiste() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setMovieTitle("Película No Existente");
        functionDTO.setProjectionRoomNumber(1);
        functionDTO.setBranchId(1);

        when(movieRepository.findByTitle("Película No Existente")).thenReturn(null);

        assertThrows(NoExiste.class, () -> functionService.addFunction(functionDTO));
    }

    @Test
    void testAddFunction_WhenRoomNotFound_ShouldThrowNoExiste() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setMovieTitle("La Invitada");
        functionDTO.setProjectionRoomNumber(1);
        functionDTO.setBranchId(1);

        when(movieRepository.findByTitle("La Invitada")).thenReturn(movie);

        when(projectionRoomRepository.findByRoomNumber(1)).thenReturn(null);

        assertThrows(NoExiste.class, () -> functionService.addFunction(functionDTO));
    }

    @Test
    void testAddFunction_WhenBranchNotFound_ShouldThrowNoExiste() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setMovieTitle("La Invitada");
        functionDTO.setProjectionRoomNumber(1);
        functionDTO.setBranchId(1);

        when(movieRepository.findByTitle("La Invitada")).thenReturn(movie);
        when(projectionRoomRepository.findByRoomNumber(1)).thenReturn(projectionRoom);

        when(branchesRepository.findByIdBran(1)).thenReturn(null);

        assertThrows(NoExiste.class, () -> functionService.addFunction(functionDTO));
    }

}