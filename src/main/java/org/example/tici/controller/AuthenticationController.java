package org.example.tici.controller;

import jakarta.servlet.http.HttpSession;
import org.example.tici.DTO.LoginRequest;
import org.example.tici.Exceptions.UsernameNotFound;
import org.example.tici.Exceptions.WrongPassword;
import org.example.tici.Model.Entities.Users;
import org.example.tici.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            Users loggedUser = userService.loadUserByEmailAndPassword(loginRequest.getMail(), loginRequest.getPassword());
            session.setAttribute("loggedUser", loggedUser);
            return ResponseEntity.ok(loggedUser);

        } catch (UsernameNotFound | WrongPassword e1) {
            e1.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }
}
