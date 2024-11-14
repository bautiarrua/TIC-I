package org.example.tici.Service;

import org.example.tici.Repository.UserRepository;
import org.example.tici.Repository.UsersRepositoryAuth;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepositoryAuth userRepository;

    public CustomUserDetailsService(UsersRepositoryAuth userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

