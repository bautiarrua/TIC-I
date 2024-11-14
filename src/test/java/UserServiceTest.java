import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.tici.Exceptions.UsernameNotFound;
import org.example.tici.Exceptions.WrongPassword;
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

@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
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

//    @Test
//    void testRegisterUser_Success() throws YaExiste {
//        Users newUser = new Users("New User", "test@mail.com", "password");
//
//        when(userRepository.findByMail(newUser.getMail())).thenReturn(null); // Verifica que no haya otro usuario con ese mail
//        when(passwordEncoder.encode(newUser.getPassword())).thenReturn("hashedPassword"); // Simula la codificación
//
//        Users registeredUser = userService.registerUser(newUser);
//
//        assertEquals("hashedPassword", registeredUser.getPassword()); // Verifica que la contraseña esté correctamente hasheada
//        verify(userRepository, times(1)).save(registeredUser); // Verifica que el usuario guardado sea el correcto
//    }

//    @Test
//    void testRegisterUser_ThrowsYaExist() throws YaExiste {
//        Users existingUser = new Users("Existing User", "test@mail.com", "password");
//
//        when(userRepository.findByMail(existingUser.getMail())).thenReturn(existingUser); // Simula que el usuario ya existe
//
//        assertThrows(YaExiste.class, () -> {
//            userService.registerUser(existingUser); // Debería lanzar la excepción
//        });
//
//        verify(userRepository, never()).save(existingUser); // Verifica que no se haya guardado el usuario
//    }

//    @Test
//    void testRegisterUser_NullEmail() throws YaExiste {
//        Users newUser = new Users("New User", null, "password");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
//        });
//
//        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
//    }

//    @Test
//    void testRegisterUser_EmptyEmail() throws YaExiste {
//        Users newUser = new Users("New User", "", "password");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
//        });
//
//        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
//    }

//    @Test
//    void testRegisterUser_NullName() throws YaExiste {
//        Users newUser = new Users(null, "test@mail.com", "password");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
//        });
//
//        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
//    }

//    @Test
//    void testRegisterUser_EmptyName() throws YaExiste {
//        Users newUser = new Users("", "test@mail.com", "password");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
//        });
//
//        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
//    }

//    @Test
//    void testRegisterUser_NullPassword() throws YaExiste {
//        Users newUser = new Users("New User", "test@mail.com", null);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
//        });
//
//        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
//    }

//    @Test
//    void testRegisterUser_EmptyPassword() throws YaExiste {
//        Users newUser = new Users("New User", "test@mail.com", "");
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(newUser); // Se espera que lance IllegalArgumentException
//        });
//
//        verify(userRepository, never()).save(newUser); // Verifica que no se haya guardado el usuario
//    }

    @Test
    void testLoadUserByEmailAndPassword_UserNotFound() {
        String email = "nonexistent@mail.com";
        String password = "password";

        when(userRepository.findByMail(email)).thenReturn(null); // Simular que no se encuentra el usuario

        assertThrows(UsernameNotFound.class, () -> {
            userService.loadUserByEmailAndPassword(email, password);
        });
    }

//    @Test
//    void testLoadUserByEmailAndPassword_WrongPassword() throws UsernameNotFound {
//        String email = "test@mail.com";
//        String wrongPassword = "wrongPassword";
//        String correctPassword = "hashedPassword";
//
//        Users user = new Users("Test User", email, correctPassword);
//
//        // Simular que se encuentra el usuario
//        when(userRepository.findByMail(email)).thenReturn(user);
//        when(passwordEncoder.matches(wrongPassword, correctPassword)).thenReturn(false);
//
//        assertThrows(WrongPassword.class, () -> {
//            userService.loadUserByEmailAndPassword(email, wrongPassword);
//        });
//    }

//    @Test
//    void testLoadUserByEmailAndPassword_Success() throws UsernameNotFound, WrongPassword {
//        String email = "test@mail.com";
//        String password = "hashedPassword";
//
//        Users user = new Users("Test User", email, password);
//
//        // Simular que se encuentra el usuario y que la contraseña coincide
//        when(userRepository.findByMail(email)).thenReturn(user);
//        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);
//
//        Users loadedUser = userService.loadUserByEmailAndPassword(email, password);
//
//        assertEquals(user, loadedUser); // Verificar que el usuario cargado es el esperado
//    }

    @Test
    void testLoadUserByEmailAndPassword_NullEmail() {
        String password = "password";

        assertThrows(IllegalArgumentException.class, () -> {
            userService.loadUserByEmailAndPassword(null, password);
        });
    }

    @Test
    void testLoadUserByEmailAndPassword_EmptyEmail() {
        String password = "password";

        assertThrows(IllegalArgumentException.class, () -> {
            userService.loadUserByEmailAndPassword("", password);
        });
    }

    @Test
    void testLoadUserByEmailAndPassword_NullPassword() {
        String email = "test@mail.com";

        assertThrows(IllegalArgumentException.class, () -> {
            userService.loadUserByEmailAndPassword(email, null);
        });
    }

    @Test
    void testLoadUserByEmailAndPassword_EmptyPassword() {
        String email = "test@mail.com";

        assertThrows(IllegalArgumentException.class, () -> {
            userService.loadUserByEmailAndPassword(email, "");
        });
    }


}
