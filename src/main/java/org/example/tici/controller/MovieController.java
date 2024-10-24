package org.example.tici.controller;

import jakarta.servlet.http.HttpSession;
import org.example.tici.DTO.BookingRequest;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Service.BookingService;
import org.example.tici.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    @Autowired
    private BookingService bookingService;

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
    public ResponseEntity<String> reserveMovie(@RequestBody BookingRequest bookingRequest, HttpSession session) {
        Users loggedUser = (Users) session.getAttribute("user");

        if (loggedUser == null) {
            return ResponseEntity.status(403).body("Please login to make a reservation");
        }
        int function = bookingRequest.getFunctionId();
        List<Integer> seatsToReserve = bookingRequest.getSeatsNumberToReserve();

        boolean success = bookingService.bookSeat(function, loggedUser, seatsToReserve);

        if (success) {
            return ResponseEntity.ok("Reservation successful!");
        }
        return ResponseEntity.status(400).body("Reservation failed.");
    }
}
