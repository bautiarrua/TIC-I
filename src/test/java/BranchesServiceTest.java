
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Repository.BranchesRepository;
import org.example.tici.Exceptions.YaExiste;


import org.example.tici.Service.BranchesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
class BranchesServiceTest {

    @Mock
    private BranchesRepository branchesRepository;

    @InjectMocks
    private BranchesService branchesService;

    private Branches branch;

    @BeforeEach
    void setUp() {
        branch = new Branches(1, "Centro", 5);
    }

    @Test
    void testAddBran_Success() {
        when(branchesRepository.findByIdBran(1)).thenReturn(null);

        Branches savedBranch = null;
        try {
            savedBranch = branchesService.addBran(branch);
        } catch (YaExiste e) {
            fail("No se esperaba una excepción.");
        }

        assertNotNull(savedBranch);
        assertEquals(1, savedBranch.getIdBran());
        verify(branchesRepository, times(1)).save(branch);
    }

    @Test
    void testAddBran_ThrowsYaExiste() {
        when(branchesRepository.findByIdBran(1)).thenReturn(branch);

        assertThrows(YaExiste.class, () -> {
            branchesService.addBran(branch);
        });

        verify(branchesRepository, never()).save(branch);
    }

    @Test
    void testAddBran_NullBranch() {
        assertThrows(IllegalArgumentException.class, () -> {
            branchesService.addBran(null);
        });
    }

    @Test
    void testAddBran_SaveThrowsException() {
        when(branchesRepository.findByIdBran(1)).thenReturn(null);
        doThrow(new RuntimeException("Error al guardar")).when(branchesRepository).save(branch);

        assertThrows(RuntimeException.class, () -> {
            branchesService.addBran(branch);
        });

        verify(branchesRepository, times(1)).findByIdBran(1);
        verify(branchesRepository, times(1)).save(branch);
    }

}
