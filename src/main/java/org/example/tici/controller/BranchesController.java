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
            branchesService.addBran(branch);
            return ResponseEntity.ok("se guardo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
