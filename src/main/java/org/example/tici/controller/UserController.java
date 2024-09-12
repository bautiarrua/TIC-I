package org.example.tici.controller;

import org.example.tici.Model.Entities.Users;
import org.example.tici.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Users> registrarUsuario(@RequestBody Users user) {
        try {
            Users nuevoUsuario = userService.registerUser(user);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception YaExiste) {
            return ResponseEntity.badRequest().build();
        }
    }
}
