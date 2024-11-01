package org.example.tici.controller;

import org.example.tici.DTO.MovieDTO;
import org.example.tici.Model.Entities.Billboard;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Repository.BillboardRepository;
import org.example.tici.Service.BillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/billboard")
public class BillboardController {
    @Autowired
    BillboardService billboardService;
    @PostMapping("/add")
    public ResponseEntity<String> addBill(@RequestBody Billboard bill){
        try {
            billboardService.addBillboard(bill);
            return ResponseEntity.ok("se guardo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovieToBillboard(@RequestBody Map<String, Object> requestData) {
        try {
            String movieTitle = (String) requestData.get("movieTitle");
            int idBill = (Integer) requestData.get("idBill");

            billboardService.addMovieToBillboard(movieTitle, idBill);
            return ResponseEntity.ok("Movie added successfully to the billboard");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("The movie or billboard does not exist");
        }
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getMovies(
            @RequestParam(required = false) Optional<Integer> branchId,
            @RequestParam(required = false) Optional<String> category,
            @RequestParam(required = false) Optional<String> language,
            @RequestParam(required = false) Optional<String> format
    ) {
        try {
            // Usamos orElse para asignar un valor predeterminado o null-safe
            Integer resolvedBranchId = branchId.orElse(null);
            String resolvedCategory = category.orElse(null);
            String resolvedLanguage = language.orElse(null);
            String resolvedFormat = format.orElse(null);

            List<Movie> filteredMovies = billboardService.getFilteredMovies(resolvedBranchId, resolvedCategory, resolvedLanguage, resolvedFormat);
            List<MovieDTO> movieDTOs = filteredMovies.stream()
                    .map(movie -> new MovieDTO(movie.getTitle(), movie.getImageUrl(), movie.getGenre(), movie.getDescription(), movie.getDuration()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(movieDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<MovieDTO>> getBillboardByBranchId(@PathVariable int id) {
        try {
            Billboard billboard = billboardService.getBillboardByBranchId(id);
            List<Movie> movies = billboard.getMovies();

            // Convertir a DTO para devolver solo los campos necesarios (título y URL)
            List<MovieDTO> movieDTOs = movies.stream()
                    .map(movie -> new MovieDTO(movie.getTitle(), movie.getImageUrl(),movie.getGenre() ,movie.getDescription(), movie.getDuration()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(movieDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
