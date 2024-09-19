package org.example.tici.controller;

import org.example.tici.Exceptions.UsernameNotFound;
import org.example.tici.Exceptions.WrongPassword;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users user) {
        try {
            Users logUser = userService.loadUserByUsername(user);
            return ResponseEntity.ok(logUser);
        } catch (UsernameNotFound e1) {
            return ResponseEntity.badRequest().build();
        } catch (WrongPassword e2) {
            return ResponseEntity.badRequest().build();
        }
    }
}
