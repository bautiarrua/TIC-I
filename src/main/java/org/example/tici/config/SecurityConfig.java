package org.example.tici.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF para simplificar pruebas
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/register").permitAll()  // Permitir acceso a /users/register
                        .requestMatchers("/billboard/addMovie").permitAll()  // Permitir acceso sin autenticación a /billboard/addMovie
                        .requestMatchers("/billboard/branch/**").permitAll()  // Permitir acceso sin autenticación a /billboard/branch/*
                        .requestMatchers("/movies/add").permitAll()
                        .requestMatchers("/branches/add").permitAll()
                        .requestMatchers("/billboard/add").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("movies/reserve").permitAll()
                        .anyRequest().authenticated()  // Requerir autenticación para el resto de los endpoints
                );

        return http.build();
    }
}
