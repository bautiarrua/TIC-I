import org.example.tici.Exceptions.LimiteAlcanzado;
import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.ProjectionRoom;
import org.example.tici.Repository.BranchesRepository;
import org.example.tici.Repository.ProjectionRoomRepository;
import org.example.tici.Service.ProjectionRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
public class ProjectionRoomServiceTest {

    @InjectMocks
    private ProjectionRoomService projectionRoomService;

    @Mock
    private ProjectionRoomRepository projectionRoomRepository;

    @Mock
    private BranchesRepository branchesRepository;

    private ProjectionRoom room;
    private Branches branch;

    @BeforeEach
    void setUp() {
        room = new ProjectionRoom();
        room.setRoomNumber(1);
        branch = new Branches();
        branch.setIdBran(1);
        branch.setCantidad(5);
        branch.setNroRooms(10);
        room.setBranch(branch);
    }

    @Test
    void testAddRoom_WhenRoomDoesNotExist_ShouldAddRoom() throws YaExiste, NoExiste, LimiteAlcanzado {
        when(projectionRoomRepository.findByRoomNumber(room.getRoomNumber())).thenReturn(null);
        when(branchesRepository.findByIdBran(branch.getIdBran())).thenReturn(branch);

        ProjectionRoom result = projectionRoomService.addRoom(room);

        verify(projectionRoomRepository).save(room);
        assertEquals(room.getRoomNumber(), result.getRoomNumber());
    }

    @Test
    void testAddRoom_WhenRoomAlreadyExists_ShouldThrowYaExiste() {
        when(projectionRoomRepository.findByRoomNumber(room.getRoomNumber())).thenReturn(room);

        assertThrows(YaExiste.class, () -> projectionRoomService.addRoom(room));
    }

    @Test
    void testAddRoom_WhenBranchDoesNotExist_ShouldThrowNoExiste() {
        when(branchesRepository.findByIdBran(branch.getIdBran())).thenReturn(null);

        assertThrows(NoExiste.class, () -> projectionRoomService.addRoom(room));
    }

    @Test
    void testAddRoom_WhenLimitReached_ShouldThrowLimiteAlcanzado() {
        branch.setCantidad(branch.getNroRooms());
        when(branchesRepository.findByIdBran(branch.getIdBran())).thenReturn(branch);

        assertThrows(LimiteAlcanzado.class, () -> projectionRoomService.addRoom(room));
    }
}
