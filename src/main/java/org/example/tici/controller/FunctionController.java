package org.example.tici.controller;

import org.example.tici.DTO.FunctionDTO;
import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Model.Entities.Function;
import org.example.tici.Repository.FunctionRepository;
import org.example.tici.Service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/functions")
public class FunctionController {

    @Autowired
    FunctionService functionService;

    @GetMapping("/fun/{idbran}/{title}/{daymonth}")
    public ResponseEntity<List<Function>> getFunctionsByBranchMovieAndDate(
            @PathVariable int idbran,
            @PathVariable String title,
            @PathVariable int daymonth) {

        List<Function> functions = functionService.getFunctionsByBranchMovieAndDate(idbran, title, daymonth);
        return ResponseEntity.ok(functions);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFunction(@RequestBody FunctionDTO functionDTO) {
        try {
            System.out.println("le manda el dto");
            functionService.addFunction(functionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Function added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding function");
        }
    }

}
