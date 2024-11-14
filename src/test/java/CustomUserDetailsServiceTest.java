import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UsersRepositoryAuth;
import org.example.tici.Service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UsersRepositoryAuth userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setMail("user@example.com");
        user.setPassword("password123");
        user.setName("Test User");
    }

    @Test
    void testLoadUserByUsername_success() {
        when(userRepository.findByMail("user@example.com")).thenReturn(java.util.Optional.of(user));

        UserDetails result = customUserDetailsService.loadUserByUsername("user@example.com");

        assertNotNull(result);
        assertEquals(user.getMail(), result.getUsername());
    }

    @Test
    void testLoadUserByUsername_userNotFound() {
        when(userRepository.findByMail("user@example.com")).thenReturn(java.util.Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("user@example.com");
        });
    }
}

