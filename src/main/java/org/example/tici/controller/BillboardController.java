package org.example.tici.controller;

import org.example.tici.Model.Entities.Billboard;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Repository.BillboardRepository;
import org.example.tici.Service.BillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            System.out.println("------------------INTENTA-------------------");
            String movieTitle = (String) requestData.get("movieTitle");
            int idBill = (Integer) requestData.get("idBill");

            billboardService.addMovieToBillboard(movieTitle, idBill);
            return ResponseEntity.ok("Movie added successfully to the billboard");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("The movie or billboard does not exist");
        }
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<String>> getBillboardByBranchId(@PathVariable int id) {
        try {
            Billboard billboard = billboardService.getBillboardByBranchId(id);
            return ResponseEntity.ok(billboard.getMovie());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
