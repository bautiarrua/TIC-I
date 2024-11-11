package org.example.tici.controller;

import jakarta.servlet.http.HttpSession;
import org.example.tici.DTO.LoginRequest;
import org.example.tici.Exceptions.UsernameNotFound;
import org.example.tici.Exceptions.WrongPassword;
import org.example.tici.Model.Entities.Users;
import org.example.tici.SecurityAuthentication.AuthResponse;
import org.example.tici.SecurityAuthentication.JwtUtil;
import org.example.tici.SecurityAuthentication.TokenBlacklistService;
import org.example.tici.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Users loggedUser = userService.loadUserByEmailAndPassword(loginRequest.getMail(), loginRequest.getPassword());

            String token = jwtUtil.generateToken(loggedUser.getMail());

            return ResponseEntity.ok(new AuthResponse(token, loggedUser));

        } catch (UsernameNotFound | WrongPassword e1) {
            e1.printStackTrace();
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session, @RequestHeader("Authorization") String token) {
        session.invalidate();

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            tokenBlacklistService.invalidateToken(jwtToken);
        }

        return ResponseEntity.ok("Logged out successfully");
    }

}
