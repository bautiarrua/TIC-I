package org.example.tici.controller;

import org.example.tici.Model.Entities.Billboard;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Service.BranchesService;
import org.example.tici.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchesController {

    @Autowired
    BranchesService branchesService;

    @PostMapping("/add")
    public ResponseEntity<String> addBranch(@RequestBody Branches branch){
        try {
            System.out.println("-----------try-------------");
            branchesService.addBran(branch);
            return ResponseEntity.ok("se guardo");
        } catch (Exception e) {
            System.out.println("-----------catch-------------");
            return ResponseEntity.badRequest().build();
        }
    }
    /*
    @GetMapping("/billboard")
    public ResponseEntity<List<Movie>> consultBillboard(@RequestBody Branches branches) {
        try {
            List<Movie> movies = branchesService.consultBillboard(branches);
            return ResponseEntity.ok(movies);
        } catch (Exception YaExiste) {
            return ResponseEntity.badRequest().build();
        }
    }

     */



}
