package org.example.tici.controller;

import org.example.tici.Model.Entities.Users;
import org.example.tici.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping ("/users")
public class UserController {
    @Autowired
    UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/register")
    public ResponseEntity<String> registrarUsuario(@RequestBody Users user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("se guardo");
        } catch (Exception e) {
            logger.error(e.toString());
            logger.error(user.toString());
            return ResponseEntity.badRequest().build();
        }
    }

}

