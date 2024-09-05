package org.example.tici.Service;

import org.example.tici.Model.Entities.Users;
import org.example.tici.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private String registerUser (Users user){
        if( userRepository.findByMail(user.getMail()) != null){
            return "Error: El Usuario ya está registrado con ese mail ";
        }
        userRepository.save(user);
        return "Usuario registrado con éxito";
    }
}
