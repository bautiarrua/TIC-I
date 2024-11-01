package org.example.tici.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.List;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración CORS
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF para permitir solicitudes desde el frontend en desarrollo
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/users/register",
                                "/auth/login",
                                "/movies/title/**",
                                "/billboard/**",
                                "/billboard/addMovie",
                                "/billboard/branch/**",
                                "/movies/add",
                                "/branches/add",
                                "/billboard/add",
                                "/billboard/movies"
                        ).permitAll() // Permite el acceso a estos endpoints sin autenticación
                        .requestMatchers("/movies/reserve").authenticated() // Requiere autenticación para reservar
                        .anyRequest().authenticated() // Todas las demás solicitudes requieren autenticación
                )
                .sessionManagement(session -> session
                        .sessionFixation(sessionFixation -> sessionFixation.migrateSession())
                        .maximumSessions(1)
                        .expiredUrl("/auth/login") // Redirigir al login si la sesión expira
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173")); // Origen permitido
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Métodos HTTP permitidos
        config.setAllowedHeaders(List.of("*")); // Permitir todos los headers
        config.setAllowCredentials(true); // Permitir credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica la configuración CORS a todas las rutas
        return source;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
