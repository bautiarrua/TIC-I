import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.tici.Exceptions.UsernameNotFound;
import org.example.tici.Exceptions.WrongPassword;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UserRepository;
import org.example.tici.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Nested
@SpringBootTest(classes = MainPrueba.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

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
    public void testRegisterUser_success() throws YaExiste {
        String mail = "user@example.com";
        Users user = new Users();
        user.setMail(mail);
        user.setName("Test User");
        user.setPassword("password123");

        when(userRepository.findByMail(mail)).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("hashedPassword");

        Users result = userService.registerUser(user);

        assertNotNull(result);
        assertEquals(user.getMail(), result.getMail());
        assertEquals("hashedPassword", result.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    public void testRegisterUser_emailAlreadyExists() {
        String mail = "user@example.com";
        Users user = new Users();
        user.setMail(mail);
        user.setName("Test User");
        user.setPassword("password123");

        when(userRepository.findByMail(mail)).thenReturn(new Users());

        assertThrows(YaExiste.class, () -> userService.registerUser(user));
    }

    @Test
    public void testRegisterUser_invalidEmail() {
        Users user = new Users();
        user.setMail("");
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(user));
    }

    @Test
    public void testRegisterUser_invalidPassword() {
        Users user = new Users();
        user.setPassword("");
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(user));
    }


    @Test
    void testLoadUserByEmailAndPassword_UserNotFound() {
        String email = "nonexistent@mail.com";
        String password = "password";

        when(userRepository.findByMail(email)).thenReturn(null);

        assertThrows(UsernameNotFound.class, () -> {
            userService.loadUserByEmailAndPassword(email, password);
        });
    }

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
