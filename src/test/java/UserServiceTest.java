import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UserRepository;
import org.example.tici.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(classes = MainPrueba.class) // Asegúrate de reemplazar 'YourApplication' con tu clase principal
@ExtendWith(MockitoExtension.class) // Usando MockitoExtension para inicializar mocks
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() throws YaExiste {
        Users newUser = new Users("New User", "test@mail.com", "password");

        when(userRepository.findByMail(newUser.getMail())).thenReturn(null); // Verifica que no haya otro usuario con ese mail
        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("hashedPassword"); // Simula la codificación

        Users registeredUser = userService.registerUser(newUser);

        assertEquals("hashedPassword", registeredUser.getPassword()); // Verifica que la contraseña esté correctamente hasheada
        verify(userRepository, times(1)).save(registeredUser); // Verifica que el usuario guardado sea el correcto
    }

    @Test
    void testRegisterUser_ThrowsYaExist() throws YaExiste {
        Users existingUser = new Users("Existing User", "test@mail.com", "password");

        when(userRepository.findByMail(existingUser.getMail())).thenReturn(existingUser); // Simula que el usuario ya existe

        assertThrows(YaExiste.class, () -> {
            userService.registerUser(existingUser); // Debería lanzar la excepción
        });

        verify(userRepository, never()).save(existingUser); // Verifica que no se haya guardado el usuario
    }

    @Test
    void testRegisterUser_NullEmail() throws YaExiste {
        Users newUser = new Users("New User", null, "password");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
        });

        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
    }

    @Test
    void testRegisterUser_EmptyEmail() throws YaExiste {
        Users newUser = new Users("New User", "", "password");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
        });

        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
    }

    @Test
    void testRegisterUser_NullName() throws YaExiste {
        Users newUser = new Users(null, "test@mail.com", "password");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
        });

        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
    }

    @Test
    void testRegisterUser_EmptyName() throws YaExiste {
        Users newUser = new Users("", "test@mail.com", "password");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
        });

        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
    }

    @Test
    void testRegisterUser_NullPassword() throws YaExiste {
        Users newUser = new Users("New User", "test@mail.com", null);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
        });

        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
    }

    @Test
    void testRegisterUser_EmptyPassword() throws YaExiste {
        Users newUser = new Users("New User", "test@mail.com", "");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
        });

        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
    }
}
