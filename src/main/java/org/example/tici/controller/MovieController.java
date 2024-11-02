package org.example.tici.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.example.tici.DTO.BookingRequest;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UserRepository;
import org.example.tici.SecurityAuthentication.JwtUtil;
import org.example.tici.Service.BookingService;
import org.example.tici.Service.MovieService;
import org.example.tici.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        try {
            movieService.addMovie(movie);
            return ResponseEntity.ok("se guardo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/reserve")
    public ResponseEntity<String> reserveMovie(@RequestBody BookingRequest bookingRequest, @RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Unauthorized: Please log in.");
        }

        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);

        if (!jwtUtil.validateToken(jwtToken, username)) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid token.");
        }

        Users loggedUser  = userService.findByMail(username); // Fetch user details from DB
        if (loggedUser  == null) {
            return ResponseEntity.status(404).body("User  not found.");
        }

        int function = bookingRequest.getFunctionId();
        List<Integer> seatsToReserve = bookingRequest.getSeatsNumberToReserve();

        boolean success = bookingService.bookSeat(function, loggedUser , seatsToReserve);
        if (success) {
            return ResponseEntity.ok("Reservation successful!");
        }
        return ResponseEntity.status(400).body("Reservation failed: Seats may already be reserved.");
    }


}
